import dayjs from 'dayjs';
import {useUserContext} from '@src/lib/store/user/consumer';
import {queries} from '@src/queries';
import orderStyles from './orders.module.less';
import {useQueries, useQuery} from 'react-query';
import {Card, Rate, Spin, Tag} from 'antd';
import {getImageSrc, mapProductRealToDTO} from '@src/lib/utils';
import {ImageDto, ProductDto, ProductReal} from '@src/lib/api-schemas';
import StripeCheckout from 'react-stripe-checkout';
import {STRIPE_PUBLISHABLE_KEY} from '@src/lib/constants';
import {useCreatePaymentIntent} from '../product/queries';
import {PrintPDF} from './print-pdf';
import {RENT, installments} from './utils';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSmile} from '@fortawesome/free-solid-svg-icons';
import {usePostReview} from './queries';

export const OrderPage = () => {
  const {user} = useUserContext();

  const {data: stripeCustomer, isLoading: stripeCustomerLoading} = useQuery({
    ...queries.payments.customer(user?.login || ''),
    enabled: !!user?.login,
  });

  const {data: payments, isLoading: paymentsLoading} = useQuery({
    ...queries.payments.intents(stripeCustomer?.id || ''),
    enabled: !!stripeCustomer?.id,
    staleTime: 10000,
  });

  const {data: orders, isLoading: ordersLoading} = useQuery({
    ...queries.user.orders(),
    placeholderData: [],
  });

  const {data: orderImages} = useQuery(
    queries.product.images(
      orders
        ?.map((o) => o.advertisementID?.productID?.productID)
        .filter(Boolean) || [],
    ),
  );

  const reviews = useQueries(
    orders?.map((order) =>
      queries.product.review(
        order?.advertisementID?.productID?.productID || '',
      ),
    ) || [],
  );

  const {mutateAsync: createPayment} = useCreatePaymentIntent();

  const {mutateAsync: postReview} = usePostReview();

  console.log({payments});

  if (stripeCustomerLoading || paymentsLoading || ordersLoading) {
    return <Spin />;
  }

  return (
    <div className={orderStyles.container}>
      <h2 className="text-lg font-bold">Your orders: </h2>
      {orders?.map((order, idx) => {
        const product = order?.advertisementID?.productID as ProductReal;

        const relatedPayments = payments?.filter(
          (p) => p.metadata?.productId === product?.productID + '',
        );

        console.log({relatedPayments});

        const lastPayment = dayjs((relatedPayments?.[0]?.created || 0) * 1000);

        const now = dayjs();

        const diffMinutes = now.diff(lastPayment, 'minute');

        const installment = installments(order, relatedPayments || []);

        let overdue = false;

        if (order?.is_emi === RENT) {
          overdue = 5 - diffMinutes <= 0;
        } else {
          overdue = installment.paid < +product.price && 5 - diffMinutes <= 0;
        }

        const address =
          relatedPayments?.filter((p) => p?.metadata?.address)[0]?.metadata
            ?.address || '';

        const firstImage = orderImages?.[idx]?.[0] as ImageDto;

        const toPay =
          order?.is_emi === 1
            ? Math.round(+product?.price / 12)
            : +product?.price;

        return (
          <Card
            key={order?.purchaseID}
            cover={firstImage ? <img src={getImageSrc(firstImage)} /> : null}
            className="my-5"
          >
            <Card.Meta
              title={product?.name}
              description={<>{product.description}</>}
            />
            <div className={orderStyles.cardBody}>
              <Tag color="blue">
                <b>Price: </b>
                {product?.price}$
              </Tag>
              {!!relatedPayments && relatedPayments?.length > 0 && (
                <>
                  <Tag color="green">
                    Last payment on{' '}
                    {dayjs(relatedPayments?.[0].created * 1000).format(
                      'MMM D, YYYY',
                    )}
                  </Tag>

                  {(installment.paid < +product?.price ||
                    order?.is_emi === RENT) && (
                    <Tag color={overdue ? 'red' : 'green-inverse'}>
                      {overdue
                        ? 'payment overdue'
                        : `next installment in ${5 - diffMinutes} minutes.`}
                    </Tag>
                  )}

                  {(installment?.left > 0 || product?.sellType === 'rent') && (
                    <div className="mt-2">
                      {product?.sellType == 'sell' && (
                        <p className="text-xs">
                          <b>{installment.left}$</b> left to pay
                        </p>
                      )}
                      <p className="text-xs">
                        Total payment: <b>{installment.paid}$</b>
                      </p>
                    </div>
                  )}

                  {!!address && (
                    <p className="mt-5 text-xs text-gray-400">{address}</p>
                  )}

                  {installment.paid >= +product?.price &&
                    order?.is_emi !== RENT && (
                      <p className="text-green-500 mt-2 text-xs font-light">
                        <FontAwesomeIcon icon={faSmile} /> No payments left.
                      </p>
                    )}

                  {overdue && (
                    <div className="mt-5">
                      <StripeCheckout
                        stripeKey={STRIPE_PUBLISHABLE_KEY}
                        token={async (token) => {
                          console.log(token);
                          const customer = stripeCustomer;

                          if (!customer) {
                            return;
                          }

                          const customerExists = !!customer;

                          if (!customerExists) {
                            throw new Error(
                              `Customer does not exist in stripe`,
                            );
                          }

                          const customerId = customer?.id;

                          console.log(customerId);

                          const paymentChunks = [
                            token.card.address_line1,
                            token.card.address_line2,
                            token.card.address_city,
                            token.card.address_zip,
                          ].filter(Boolean);

                          const paymentIntent = await createPayment({
                            data: {
                              product: mapProductRealToDTO(
                                product,
                              ) as ProductDto,
                              customerId,
                              tokenId: token?.id,
                              address: paymentChunks.join('\n'),
                              price: toPay,
                            },
                          });

                          console.log(paymentIntent);
                        }}
                        amount={toPay * 100}
                        name={product?.name}
                        description={product?.description}
                        image={getImageSrc(firstImage)}
                        currency="CAD"
                        email={user?.login}
                        shippingAddress
                      />
                    </div>
                  )}
                </>
              )}

              <PrintPDF payments={relatedPayments || []} order={order} />
            </div>

            <span className="block mt-3 text-xs">Rate: </span>
            <Rate
              value={reviews[idx]?.data}
              onChange={(value) => {
                postReview({
                  productId: product?.productID,
                  review: {
                    user,
                    product,
                    rating: value,
                  },
                });
              }}
            />
          </Card>
        );
      })}
    </div>
  );
};
