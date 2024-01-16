import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import formStyles from './form.module.less';
import {faPlus} from '@fortawesome/free-solid-svg-icons';
import {Controller, useForm} from 'react-hook-form';
import {yupResolver} from '@hookform/resolvers/yup';
import {addProductSchema} from '../utils';
import {
  Button,
  Carousel,
  Input,
  Segmented,
  Select,
  Upload,
  UploadFile,
} from 'antd';
import TextArea from 'antd/es/input/TextArea';
import {ReactNode, useState} from 'react';
import {getImageSrc, withFirstLetterUppercase} from '@src/lib/utils';
import {ProductReal} from '@src/lib/api-schemas';
import {getBase64} from '@src/pages/settings/utils';
import {RcFile} from 'antd/es/upload';
import {useMutation, useQueries, useQuery} from 'react-query';
import {customFetch} from '@src/queries/base';
import {queries} from '@src/queries';

type ProductFormProps = {
  product?: typeof addProductSchema.__outputType;
  onSubmit: (
    product: typeof addProductSchema.__outputType,
  ) => Promise<ProductReal> | void;
  isLoading?: boolean;
  Header: ReactNode;
  buttonLabel?: string;
  id?: string;
};

export const ProductForm = ({
  product,
  onSubmit,
  isLoading,
  Header,
  buttonLabel = 'Add To Catalog',
  id,
}: ProductFormProps) => {
  const [sellType, setSellType] = useState<'rent' | 'sell'>(
    (product?.sellType as 'rent' | 'sell') || 'rent',
  );
  const [fileList, setFileList] = useState<UploadFile[]>([]);
  const [imagesPreview, setImagesPreview] = useState<string[]>([]);

  const {control, handleSubmit} = useForm({
    resolver: yupResolver(addProductSchema),
    defaultValues: {
      ...product,
      appliance_type: '',
      manufacture: '',
      year_of_public: '',
      year_of_purchase: '',
      model_name: '',
      gadget_type: '',
      furniture_type: '',
      condition: '',
      author: '',
      book_condition: '',
    },
  });

  const {data: images} = useQuery(queries.product.images([id || '']));

  const [areaList, categoriesList] = useQueries([
    queries.product.area,
    queries.product.categories,
  ]);

  const {mutateAsync: uploadFiles} = useMutation(
    async ({images, id}: {images: UploadFile[]; id: string}) => {
      for (let i = 0; i < images.length; i++) {
        const formData = new FormData();
        const image = images[i];
        const fileName = encodeURI(`${image.uid}`);
        formData.append('file', image.originFileObj as RcFile, fileName);
        formData.append('id', `${id}`);
        formData.append('type', 'product');

        await customFetch(
          `api/image/upload`,
          {
            method: 'POST',
            body: formData,
          },
          {ignoreRedirectOnFail: true, noOverrideHeaders: true},
        );
      }
    },
  );

  const _onSubmit = handleSubmit(async (product) => {
    if (fileList?.length > 0 && id) {
      await uploadFiles({images: fileList, id});
    }
    await onSubmit({...product, sellType})?.then((product) => {
      if (!id) {
        return uploadFiles({images: fileList, id: product.productID});
      }
    });
  });

  return (
    <div className={formStyles.container}>
      {Header}

      <div className={'mt-10'}>
        <div className={formStyles.section}>
          <div className={formStyles['non-image']}>
            <div className={formStyles.form}>
              <label className={formStyles.label}>Product name: </label>
              <Controller
                control={control}
                name="name"
                render={({field}) => (
                  <Input
                    value={field.value}
                    onChange={field.onChange}
                    placeholder="Toaster"
                  />
                )}
              />
            </div>

            <div className={formStyles.form}>
              <label className={formStyles.label}>Product description: </label>
              <Controller
                control={control}
                name="description"
                render={({field}) => (
                  <TextArea value={field.value} onChange={field.onChange} />
                )}
              />
            </div>

            <div className={formStyles.form}>
              <label className={formStyles.label}>Sell Type: </label>
              <Segmented
                options={[
                  {value: 'rent', label: 'Rent'},
                  {value: 'sell', label: 'Sell'},
                ]}
                value={sellType}
                onChange={(value) => setSellType(value as 'rent' | 'sell')}
              />
            </div>

            <div className={formStyles.form}>
              <label className={formStyles.label}>
                {withFirstLetterUppercase(sellType)} Price ($):
              </label>
              <Controller
                control={control}
                name="price"
                render={({field}) => (
                  <Input
                    type="number"
                    value={field.value}
                    onChange={field.onChange}
                    defaultValue={0}
                    min={0}
                  />
                )}
              />
            </div>

            <div className={formStyles.form}>
              <label className={formStyles.label}>Category:</label>
              <Controller
                control={control}
                name="category"
                render={({field}) => (
                  <Select
                    value={field.value?.name}
                    options={categoriesList.data?.map((cat) => ({
                      value: cat.categoryID,
                      label: cat.name,
                    }))}
                    className="w-full"
                    onChange={(id) =>
                      field.onChange(
                        categoriesList.data?.find(
                          (cat) => cat.categoryID === id,
                        ),
                      )
                    }
                  />
                )}
              />
            </div>

            <div className={formStyles.form}>
              <label className={formStyles.label}>Area:</label>
              <Controller
                control={control}
                name="area"
                render={({field}) => (
                  <Select
                    className="w-full"
                    value={field.value?.city}
                    options={areaList.data?.map((ar) => ({
                      value: ar.areaID,
                      label: ar.city,
                    }))}
                    onChange={(id) =>
                      field.onChange(
                        areaList.data?.find((ar) => ar.areaID === id),
                      )
                    }
                  />
                )}
              />
            </div>
          </div>

          <div className={formStyles.image}>
            <div className={formStyles.form}>
              <label className={formStyles.label}>Images: </label>
              <Upload
                fileList={fileList}
                onChange={(info) => {
                  setFileList(info.fileList);
                  Promise.all(
                    info.fileList.map((file) =>
                      getBase64(file?.originFileObj as RcFile),
                    ),
                  ).then(setImagesPreview);
                }}
                accept="image/jpg,jpeg,png"
                beforeUpload={() => false}
              >
                <Button icon={<FontAwesomeIcon icon={faPlus} />} type="dashed">
                  Add Image
                </Button>
              </Upload>
              <Carousel dotPosition="bottom" className={formStyles.carousel}>
                {imagesPreview.map((img) => (
                  <img
                    key={img}
                    src={img}
                    className={formStyles['carousel-image']}
                  />
                ))}
                {images?.[0]?.map((image) => (
                  <img
                    key={image.id}
                    src={getImageSrc(image)}
                    className={formStyles['carousel-image']}
                  />
                ))}
              </Carousel>
            </div>
          </div>
        </div>

        <Button type="primary" loading={isLoading} onClick={() => _onSubmit()}>
          {buttonLabel}
        </Button>
      </div>
    </div>
  );
};
