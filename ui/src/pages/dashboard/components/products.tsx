import {useNavigate} from 'react-router-dom';
import productStyles from './products.module.less';
import {ImageDto, ProductDto, ProductReal} from '@src/lib/api-schemas';
import {Popconfirm, Rate, Spin} from 'antd';
import {faTrash} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {useMutation, useQueries, useQuery} from 'react-query';
import {customFetch} from '@src/queries/base';
import {getImageSrc, mapDTOToProductReal} from '@src/lib/utils';
import {queryClient} from '@src/queries/client';
import {queries} from '@src/queries';
import {useUserContext} from '@src/lib/store/user/consumer';

type ProductsProps = {
  products: Partial<ProductDto>[];
  onNavigate?: (product: Partial<ProductDto>) => void;
  isOwner?: boolean;
};

export const Products = ({products, onNavigate, isOwner}: ProductsProps) => {
  const {user} = useUserContext();
  const navigate = useNavigate();

  const {mutate: deleteProduct, isLoading: deletingProduct} = useMutation(
    (product: Partial<ProductReal>) =>
      customFetch(
        `api/user/product`,
        {
          method: 'DELETE',
          body: JSON.stringify(product),
        },
        {ignoreRedirectOnFail: true},
      ).then(() => {
        queryClient.invalidateQueries(queries.product.bySeller.queryKey);
      }),
  );

  const {data: images, isLoading: imagesLoading} = useQuery(
    queries.product.images(
      products.map((i) => i.productID || '').filter(Boolean),
    ),
  );

  const reviews = useQueries(
    products?.map((product) => ({
      ...queries.product.review(product?.productID || ''),
      enabled: !!user,
    })) || [],
  );

  if (imagesLoading) {
    return <Spin />;
  }

  return (
    <div className={productStyles.products}>
      {products.map((product, idx) => (
        <div
          key={idx}
          className={productStyles.product}
          onClick={() => {
            if (onNavigate) {
              onNavigate(product);
              return;
            }
            navigate(`/product/${product.productID}`);
          }}
        >
          {images?.[idx]?.[0] && (
            <img src={getImageSrc(images?.[idx]?.[0] as ImageDto)} />
          )}
          {/* <img src={getRandomImages()?.[0]} /> */}
          <div className={productStyles['product-details']}>
            <span className="text-sm font-light">{product.productName}</span>
            <span className="text-xs text-gray-400 mt-5">Rent</span>
            <span className="text-sm">${product.price}/mo</span>
            <Rate disabled value={reviews[idx]?.data} />
            {isOwner && (
              <Popconfirm
                title={`Are you sure want to delete product ${product?.productName}`}
                onConfirm={(e) => {
                  e?.stopPropagation();
                  deleteProduct(mapDTOToProductReal(product));
                }}
              >
                <FontAwesomeIcon
                  icon={faTrash}
                  className="text-red-500 mt-5"
                  onClick={(e) => {
                    e.stopPropagation();
                  }}
                  spin={deletingProduct}
                />
              </Popconfirm>
            )}
          </div>
        </div>
      ))}
    </div>
  );
};
