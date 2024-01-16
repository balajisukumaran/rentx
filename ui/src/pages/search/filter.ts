import {ProductDto} from '@src/lib/api-schemas';
import {SORT_BY, SearchFilter} from './types';

export const rawSearchToFilter = (rawSearch: URLSearchParams): SearchFilter => {
  try {
    return JSON.parse(rawSearch.get('filter') || '');
  } catch {
    return {};
  }
};

export const filterToRawSearch = (
  search: URLSearchParams,
  filter: SearchFilter,
): URLSearchParams => {
  const newSearch = new URLSearchParams(search);

  newSearch.set('filter', JSON.stringify(filter));

  return newSearch;
};

export const filterProducts = (
  products: ProductDto[],
  filters: SearchFilter,
): ProductDto[] =>
  products
    .filter((product) => {
      if (
        filters.nameOrDescription &&
        !filters.nameOrDescription
          .toLowerCase()
          .includes(product.productName.toLowerCase())
      ) {
        return false;
      }

      if (
        filters.rentRange?.length === 2 &&
        !(
          product.price >= filters.rentRange[0] &&
          product.price <= filters.rentRange[1]
        )
      ) {
        return false;
      }

      if (
        filters.categories?.length &&
        !filters.categories.includes(product.categoryName)
      ) {
        return false;
      }

      if (
        filters.location?.length &&
        !filters.location.includes(product.city)
      ) {
        return false;
      }

      return true;
    })
    .sort((a, b) => {
      if (filters.sortBy === SORT_BY.NONE) {
        return 0;
      }

      if (filters.sortBy === SORT_BY.PRICE_LOW_TO_HIGH) {
        return a.price - b.price;
      }

      return b.price - a.price;
    });

export const getSortByLabel = (label: SORT_BY) => {
  switch (label) {
    case SORT_BY.PRICE_HIGH_TO_LOW:
      return 'Price high to low';
    case SORT_BY.PRICE_LOW_TO_HIGH:
      return 'Price low to high';
    case SORT_BY.NONE:
      return 'none';
  }
};
