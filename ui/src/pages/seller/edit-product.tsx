import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faArrowLeft} from '@fortawesome/free-solid-svg-icons';
import {useNavigate, useParams} from 'react-router-dom';
import {Spin, notification} from 'antd';
import {useMutation, useQuery} from 'react-query';
import {customFetch} from '@src/queries/base';
import {queries} from '@src/queries';
import {ProductForm} from './components/form';
import {addProductSchema} from './utils';

export const EditProduct = () => {
  const params = useParams();
  const navigate = useNavigate();

  const productId = params.id;

  const {data: product, isLoading: productLoading} = useQuery({
    ...queries.product.get(productId || ''),
    enabled: !!productId,
    placeholderData: null,
  });

  const {mutate: editProduct, isLoading: addingProduct} = useMutation(
    (product: typeof addProductSchema.__outputType) =>
      customFetch(
        `api/user/product`,
        {
          method: 'PUT',
          body: JSON.stringify({
            ...product,
            productID: productId,
          }),
        },
        {ignoreRedirectOnFail: true},
      ).then(() => {
        notification.success({
          message: 'Updated product successfully!',
          placement: 'bottomRight',
        });
      }),
  );

  return (
    <>
      {productLoading && <Spin />}
      {!productLoading && product && (
        <ProductForm
          Header={
            <div className="flex items-center gap-10">
              <FontAwesomeIcon
                icon={faArrowLeft}
                className="cursor-pointer text-xl text-gray-800 hover:text-gray-500"
                onClick={() => navigate(-1)}
              />

              <h6 className="font-extralight">Edit Product</h6>
            </div>
          }
          onSubmit={(product) => editProduct(product)}
          isLoading={addingProduct}
          product={{
            name: product?.productName || '',
            area: {
              areaID: product?.areaId || 0,
              city: product?.city || '',
            },
            category: {
              categoryID: product?.categoryId || 0,
              name: product?.categoryName || '',
            },
            description: product?.productDescription || '',
            price: product?.price || 0,
            sellType: product?.sellType,
          }}
          buttonLabel="Update"
          id={productId}
        />
      )}
    </>
  );
};
