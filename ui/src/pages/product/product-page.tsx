import productStyles from './product.module.less';
import {ErrorPage} from '@src/lib/components/ErrorPage';
import {Carousel, Checkbox, Spin, Tag} from 'antd';
import StripeCheckout from 'react-stripe-checkout';
import classNames from 'classnames';
import {useNavigate, useParams} from 'react-router-dom';
import {calculateDeposit} from './utils';
import {useQuery} from 'react-query';
import {queries} from '@src/queries';
import {useUserContext} from '@src/lib/store/user/consumer';
import {useMemo, useState} from 'react';
import {filterToRawSearch} from '../search/filter';
import {useWishlist} from '../wishlist/utils';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faHeart as faHeartRegular} from '@fortawesome/free-regular-svg-icons';
import {
  faHeart as faHeartSolid,
  faPencil,
} from '@fortawesome/free-solid-svg-icons';
import {STRIPE_PUBLISHABLE_KEY} from '@src/lib/constants';
import {
  useCreateCustomer,
  useCreatePaymentIntent,
  usePurchaseProduct,
} from './queries';
import {getImageSrc} from '@src/lib/utils';
import {ImageDto} from '@src/lib/api-schemas';

export const ProductPage = () => {
  const navigate = useNavigate();
  const {address, user} = useUserContext();
  const params = useParams();

  const productId = params.id;

  const [wishlist, addWishlist, removeWishlist, loadingWishlist] =
    useWishlist();

  const isProductInWishlist = wishlist?.find(
    (p) => +p.productID === +(productId || -1),
  );

  const {
    data: product,
    isLoading,
    error,
  } = useQuery({
    ...queries.product.get(productId || ''),
    enabled: !!productId,
    placeholderData: null,
  });

  const {data: stripeCustomer} = useQuery({
    ...queries.payments.customer(user?.login || ''),
    enabled: !!user,
  });

  const {data: customerIntents} = useQuery({
    ...queries.payments.intents(stripeCustomer?.id || ''),
    enabled: !!stripeCustomer?.id,
    staleTime: 10000,
  });

  console.log(customerIntents);

  const {mutateAsync: createCustomer} = useCreateCustomer();

  const {mutateAsync: createPayment} = useCreatePaymentIntent();

  const isOwner = user?.userID === +(product?.userId || 0);

  const {mutateAsync: purchaseProduct} = usePurchaseProduct();

  // hack
  const {data: products} = useQuery({
    ...queries.product.list(),
    placeholderData: [],
  });

  const categories = useMemo(() => {
    const availableCategoriesSet = new Set<string>();

    for (const product of products || []) {
      availableCategoriesSet.add(product.categoryName);
    }

    return Array.from(availableCategoriesSet).map((a) => ({name: a}));
  }, [products]);

  const {data: images} = useQuery({
    ...queries.product.images([productId || '']),
    enabled: !!productId,
  });

  const [emiEnabled, setEMIEnabled] = useState(false);

  const toPay = emiEnabled
    ? Math.round((product?.price || 0) / 12)
    : product?.price || 0;

  if (error) {
    return <ErrorPage message={JSON.stringify(error)} />;
  }

  if (isLoading || !product) {
    return <Spin />;
  }

  if (!product?.productID) {
    return (
      <ErrorPage message="This product does not exist. If it was then it is already sold" />
    );
  }

  const availableInUsersCity = product.city
    ?.toLowerCase()
    ?.includes(address?.toLowerCase() || '');

  return (
    <>
      <div className={productStyles.categories}>
        {categories.map((category) => {
          const isInCategory = category.name
            .toLowerCase()
            .includes(product.categoryName?.toLowerCase() || '');
          return (
            <div
              key={category.name}
              className={classNames('text-xs cursor-pointer', {
                'text-blue': isInCategory,
                'text-gray-400': !isInCategory,
              })}
              onClick={() => {
                const search = filterToRawSearch(new URLSearchParams(), {
                  categories: [category.name],
                });

                navigate(`/search?${search.toString()}`);
              }}
            >
              {category.name}
            </div>
          );
        })}

        {isOwner && (
          <FontAwesomeIcon
            icon={faPencil}
            className="ml-auto cursor-pointer"
            onClick={() => navigate(`/product/${productId}/edit`)}
          />
        )}
      </div>
      <div className={productStyles.container}>
        <div className={productStyles.details}>
          <Carousel
            dotPosition="bottom"
            className={productStyles.carousel}
            dots={{className: productStyles.dots}}
          >
            {images?.[0]?.map((image) => (
              <img
                className={productStyles['main-image']}
                src={getImageSrc(image)}
                key={image.id}
              />
            ))}
          </Carousel>

          <div className={productStyles['details-content']}>
            <h6>Details</h6>

            <p>{product.productDescription}</p>
          </div>
        </div>
        <div className={productStyles.cta}>
          {!isOwner && !availableInUsersCity && (
            <div>
              <Tag color="red" className="mb-2">
                not available in your city
              </Tag>
            </div>
          )}

          <span>{product.productName}</span>

          <div className={productStyles.box}>
            {product.sellType === 'sell' ? (
              <>
                <div>
                  <p>${product.price}</p>
                  <span className={productStyles.label}>Sell Price</span>
                </div>
                <div className="w-1 border-r border-r-gray-100" />
                <div>
                  <p>${Math.round(product.price / 12)}/month</p>
                  <span className={productStyles.label}>12 Months EMI</span>
                </div>
              </>
            ) : (
              <>
                <div>
                  <p>${product.price}/month</p>
                  <span className={productStyles.label}>Monthly rent</span>
                </div>
                <div className="w-1 border-r border-r-gray-100" />
                <div>
                  <p>${calculateDeposit(product.price || 0)}</p>
                  <span className={productStyles.label}>
                    Refundable Deposit
                  </span>
                </div>
              </>
            )}
          </div>

          {user?.login && !isOwner && (
            <>
              <span className="text-xs mb-2 block">
                {product?.sellType === 'rent' ? 'Rent' : 'Purchase'}
              </span>
              <StripeCheckout
                stripeKey={STRIPE_PUBLISHABLE_KEY}
                token={async (token) => {
                  console.log(token);
                  const customer = stripeCustomer;

                  const customerExists = !!customer;

                  let customerId;
                  if (!customerExists) {
                    const customerCreate = await createCustomer(
                      user?.login || '',
                    );

                    customerId = customerCreate?.id;
                  } else {
                    customerId = customer?.id;
                  }

                  console.log(customerId);

                  const paymentChunks = [
                    token.card.address_line1,
                    token.card.address_line2,
                    token.card.address_city,
                    token.card.address_zip,
                  ].filter(Boolean);
                  const paymentIntent = await createPayment({
                    data: {
                      product,
                      customerId,
                      tokenId: token?.id,
                      address: paymentChunks.join('\n'),
                      price: toPay,
                    },
                  });

                  console.log(paymentIntent);

                  await purchaseProduct({
                    product,
                    buyerId: user?.userID + '',
                    pay: toPay,
                    isEmi: emiEnabled,
                  });
                }}
                amount={toPay * 100}
                name={product?.productName}
                description={product?.productDescription}
                image={getImageSrc(images?.[0]?.[0] as ImageDto)}
                currency="CAD"
                email={user?.login}
                shippingAddress
                allowRememberMe
              />
              {product?.sellType === 'sell' && (
                <Checkbox
                  className="mt-1"
                  onChange={(e) => setEMIEnabled(e.target.checked)}
                >
                  <span className="text-xs font-bold">Pay EMI</span>
                </Checkbox>
              )}
            </>
          )}

          <div className="mt-5">
            <p className="text-xs font-light text-gray-500">
              posted at {new Date(product.postDate).toLocaleString()}
            </p>

            <p className="text-xs mt-2">
              by {isOwner ? 'You' : `${product.firstName} ${product.lastName}`}
            </p>
          </div>

          {!isOwner && !!user && (
            <div className="mt-5">
              {!loadingWishlist && (
                <Tag
                  color="green"
                  className="cursor-pointer"
                  onClick={() => {
                    if (isProductInWishlist) {
                      removeWishlist(product);
                    } else {
                      addWishlist(product);
                    }
                  }}
                >
                  <FontAwesomeIcon
                    icon={isProductInWishlist ? faHeartSolid : faHeartRegular}
                    className="mr-2"
                  />
                  {isProductInWishlist
                    ? 'Remove from wishlist'
                    : 'Add to wishlist'}
                </Tag>
              )}
              {loadingWishlist && <Spin />}
            </div>
          )}
        </div>
      </div>
    </>
  );
};
