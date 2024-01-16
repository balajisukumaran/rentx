import {ProductDto} from '@src/lib/api-schemas';
import {useUserContext} from '@src/lib/store/user/consumer';
import {queries} from '@src/queries';
import {customFetch} from '@src/queries/base';
import {queryClient} from '@src/queries/client';
import {useMutation, useQuery} from 'react-query';

export const __getWishlistProducts = (): ProductDto[] => {
  const products = JSON.parse(sessionStorage.getItem('wishlist') || '[]');

  return products;
};

export const __setWishlistProducts = (products: ProductDto[]) => {
  sessionStorage.setItem('wishlist', JSON.stringify(products));
};

export const __addWishlistProduct = (product: ProductDto): ProductDto[] => {
  const products = __getWishlistProducts() || [];

  if (!products.find((_product) => _product.productID === product.productID)) {
    products.push(product);
  }

  __setWishlistProducts(products);

  return products;
};

export const useWishlist = () => {
  const {user} = useUserContext();

  const {data: wishlistProducts, isLoading: wishlistProductsLoading} = useQuery(
    {
      ...queries.product.wishList,
      placeholderData: [],
      enabled: !!user,
    },
  );

  const {mutate: addWishlistProduct, isLoading: addWishlistProductLoading} =
    useMutation((product: ProductDto) =>
      customFetch(`api/wishlist/${product.productID}`, {
        method: 'POST',
      }).then(() => {
        queryClient.invalidateQueries(queries.product.wishList.queryKey);
      }),
    );

  const {
    mutate: removeWishlistProduct,
    isLoading: removeWishlistProductLoading,
  } = useMutation((product: ProductDto) =>
    customFetch(
      `api/wishlist/${product.productID}`,
      {
        method: 'DELETE',
      },
      {ignoreRedirectOnFail: true},
    ).then(() => {
      queryClient.invalidateQueries(queries.product.wishList.queryKey);
    }),
  );

  return [
    wishlistProducts,
    addWishlistProduct,
    removeWishlistProduct,
    wishlistProductsLoading ||
      addWishlistProductLoading ||
      removeWishlistProductLoading,
  ] as const;
};
