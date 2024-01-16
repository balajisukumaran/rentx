import {createQueryKeys} from '@lukemorales/query-key-factory';
import {customFetch} from './base';
import {
  ImageDto,
  ProductDto,
  ProductReal,
  TArea,
  TCategory,
  TReview,
} from '@src/lib/api-schemas';
import {getRandomImages} from '@src/temp';

const imageMapper: Record<string, string[]> = {};

const getImages = (id: string) => {
  imageMapper[id] = imageMapper[id] || getRandomImages();
  return imageMapper[id];
};

export const product = createQueryKeys('product', {
  list: () => ({
    queryKey: ['products'],
    queryFn: (): Promise<ProductDto[]> =>
      customFetch('api/home/products')
        .then((res) => res.json())
        .then((products: ProductDto[]) =>
          products.map((p) => ({...p, images: getImages(p.productID)})),
        ),
  }),
  get: (id: string) => ({
    queryKey: ['products', id],
    queryFn: (): Promise<ProductDto> =>
      customFetch(`api/home/products/${id}`)
        .then((res) => res.json())
        .catch(() => {})
        .then((p) => (!p ? {} : p))
        .then((p: ProductDto) => ({...p, images: getImages(p.productID)})),
  }),
  wishList: {
    queryKey: ['wishlist'],
    queryFn: (): Promise<ProductReal[]> =>
      customFetch(`api/wishlist`, {}, {ignoreRedirectOnFail: true})
        .then((res) => res.json())
        .then((products: ProductReal[]) =>
          products.map((product) => ({
            ...product,
            images: getImages(product.productID),
          })),
        ),
  },
  bySeller: {
    queryKey: ['by-seller'],
    queryFn: (): Promise<ProductReal[]> =>
      customFetch(`api/user/product`).then((res) => res.json()),
  },
  images: (productId: string[]) => ({
    queryKey: ['images', ...productId],
    queryFn: () =>
      Promise.all(
        productId.map((d) => {
          return customFetch(
            `api/image/details`,
            {},
            {queryString: `?type=product&id=${d}`, ignoreRedirectOnFail: true},
          )
            .then((res) => res.json())
            .then((imageDetails: ImageDto[]) => {
              return imageDetails;
            })
            .catch(console.error);
        }),
      ),
  }),
  area: {
    queryKey: [''],
    queryFn: (): Promise<TArea[]> =>
      customFetch(`api/areas`).then((res) => res.json()),
  },
  categories: {
    queryKey: [''],
    queryFn: (): Promise<TCategory[]> =>
      customFetch(`api/categories`).then((res) => res.json()),
  },
  review: (productId: string) => ({
    queryKey: [productId],
    queryFn: (): Promise<number> =>
      customFetch(`api/products/review/${productId}`)
        .then((res) => res.json() as Promise<TReview[]>)
        .then(
          (reviews) =>
            reviews.reduce((acc, current) => acc + current.rating, 0) /
            reviews.length,
        )
        .then((acc) => acc || 0),
  }),
});
