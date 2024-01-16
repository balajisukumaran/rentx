import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faArrowLeft} from '@fortawesome/free-solid-svg-icons';
import {useNavigate} from 'react-router-dom';
import {notification} from 'antd';
import {useMutation} from 'react-query';
import {customFetch} from '@src/queries/base';
import {ProductForm} from './components/form';
import {addProductSchema} from './utils';
import {ProductReal} from '@src/lib/api-schemas';

export const AddProduct = () => {
  const navigate = useNavigate();

  const {mutateAsync: addProduct, isLoading: addingProduct} = useMutation(
    (product: typeof addProductSchema.__outputType): Promise<ProductReal> =>
      customFetch(`api/user/product`, {
        method: 'POST',
        body: JSON.stringify(product),
      }).then((res) => {
        notification.success({
          message: 'Added product successfully!',
          placement: 'bottomRight',
        });
        return res.json();
      }),
  );

  return (
    <>
      <ProductForm
        Header={
          <div className="flex items-center gap-10">
            <FontAwesomeIcon
              icon={faArrowLeft}
              className="cursor-pointer text-xl text-gray-800 hover:text-gray-500"
              onClick={() => navigate(-1)}
            />

            <h6 className="font-extralight">Add Product</h6>
          </div>
        }
        onSubmit={(product) => addProduct(product)}
        isLoading={addingProduct}
      />
    </>
  );
};
