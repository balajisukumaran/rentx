import sellerStyles from './seller.module.less';
import {useUserContext} from '@src/lib/store/user/consumer';
import {
  isUserSeller,
  isUserVerifiedSeller,
  mapProductRealToDTO,
  userAsUnverifiedSeller,
  userAsVerifiedSeller,
} from '@src/lib/utils';
import BecomeSellerIllustration from '@src/assets/become-seller-illustration.svg';
import VerificationIllustration from '@src/assets/verification-illustration.svg';
import {Button, Popconfirm, Spin, Tabs, Upload, notification} from 'antd';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {
  faBoxesPacking,
  faCheckCircle,
  faCircleNotch,
  faPlus,
  faStar,
  faStore,
} from '@fortawesome/free-solid-svg-icons';
import {useMutation, useQuery} from 'react-query';
import {customFetch} from '@src/queries/base';
import {queryClient} from '@src/queries/client';
import {queries} from '@src/queries';
import classNames from 'classnames';
import {Products} from '../dashboard/components/products';
import {useNavigate} from 'react-router-dom';

export const SellerPage = () => {
  const navigate = useNavigate();
  const {user} = useUserContext();

  const {mutate: becomeSeller, isLoading: becomingSeller} = useMutation(
    (verified?: boolean) =>
      customFetch(`api/profile/${user?.userID}`, {
        method: 'PUT',
        body: JSON.stringify(
          verified ? userAsVerifiedSeller(user) : userAsUnverifiedSeller(user),
        ),
      }).then(() => {
        queryClient.invalidateQueries(queries.user.getByToken._def);
        notification.success({
          message: 'Congrats! You are seller now.',
          placement: 'bottomRight',
        });
      }),
  );

  const {data: products, isLoading: isProductsLoading} = useQuery({
    ...queries.product.bySeller,
    placeholderData: [],
  });

  if (!isUserSeller(user)) {
    return (
      <div className={sellerStyles['container']}>
        <div className={sellerStyles['image']}>
          <img src={BecomeSellerIllustration} />
        </div>
        <div className={sellerStyles['content']}>
          <h6>
            <FontAwesomeIcon icon={faStore} className="mr-3" /> Start Selling
          </h6>
          <ul>
            <li>Generate Additional Income</li>
            <li>Build a Reputation and Trust</li>
            <li>Flexible and Hassle-Free Management</li>
          </ul>
          <Popconfirm
            title="Become a Seller"
            description="By confirming you will become a seller and will be able to add, remove and update products. You can verify your identity or skip identification."
            icon={
              <FontAwesomeIcon
                icon={faCircleNotch}
                className="mr-3 text-blue"
              />
            }
            overlayClassName="w-1/3"
            onConfirm={() => becomeSeller(false)}
          >
            <Button type="primary" loading={becomingSeller}>
              Become a Seller
            </Button>
          </Popconfirm>
        </div>
      </div>
    );
  }

  return (
    <Tabs
      type="card"
      size="large"
      items={[
        {
          key: 'catalog',
          label: (
            <>
              <FontAwesomeIcon icon={faBoxesPacking} className="mr-3" />
              Catalog
            </>
          ),
          children: (
            <>
              <div className="flex w-full">
                <Button
                  icon={<FontAwesomeIcon icon={faPlus} />}
                  type="primary"
                  className="ml-auto mt-5"
                  onClick={() => navigate('/product/add')}
                >
                  Sell Product
                </Button>
              </div>

              {isProductsLoading && <Spin />}

              {!isProductsLoading && (
                <Products
                  products={
                    products?.map((product) => mapProductRealToDTO(product)) ||
                    []
                  }
                  isOwner
                />
              )}
            </>
          ),
        },
        {
          key: 'verification',
          label: (
            <>
              <FontAwesomeIcon
                icon={faStar}
                className={classNames('mr-3', {
                  'text-green-500': isUserVerifiedSeller(user),
                })}
              />
              Verification
            </>
          ),
          children: isUserVerifiedSeller(user) ? (
            <h2 className="font-light text-center mt-10">
              <FontAwesomeIcon
                icon={faCheckCircle}
                className="text-green-400 mr-5"
              />
              You are already verified.
            </h2>
          ) : (
            <div className={sellerStyles.container}>
              <div className={sellerStyles.image}>
                <img src={VerificationIllustration} />
              </div>
              <div className={sellerStyles.content}>
                Verify yourself by uploading a pdf contains your legal identity.
                <Upload
                  accept="application/pdf"
                  maxCount={1}
                  className="mt-5"
                  beforeUpload={() => false}
                >
                  <Button>Upload PDF</Button>
                </Upload>
                <Button
                  type="primary"
                  className="mt-10"
                  onClick={() => becomeSeller(true)}
                  loading={becomingSeller}
                >
                  Verify
                </Button>
              </div>
            </div>
          ),
        },
      ]}
    />
  );
};
