import {ErrorPage} from '@src/lib/components/ErrorPage';
import {queries} from '@src/queries';
import {Select, Slider, Spin} from 'antd';
import {useQuery} from 'react-query';
import {Products} from '@src/pages/dashboard/components/products';
import searchStyle from './search.module.less';
import {useSearchParams} from 'react-router-dom';
import {useMemo} from 'react';
import {useDebounce} from 'use-debounce';
import {
  filterProducts,
  filterToRawSearch,
  getSortByLabel,
  rawSearchToFilter,
} from './filter';
import {SORT_BY} from './types';

export const SearchPage = () => {
  const [search, setSearch] = useSearchParams();

  const filter = useMemo(() => rawSearchToFilter(search), [search]);

  const {
    data: unfilteredProducts,
    isLoading,
    error,
  } = useQuery({
    ...queries.product.list(),
    placeholderData: [],
  });

  const _products = useMemo(
    () => filterProducts(unfilteredProducts || [], filter),
    [filter, unfilteredProducts],
  );

  const [products] = useDebounce(_products, 1000);

  const availableCategories = useMemo(() => {
    const availableCategoriesSet = new Set<string>();

    for (const product of unfilteredProducts || []) {
      availableCategoriesSet.add(product.categoryName);
    }

    return Array.from(availableCategoriesSet);
  }, [unfilteredProducts]);

  const availableLocation = useMemo(() => {
    const availableLocationSet = new Set<string>();

    for (const product of unfilteredProducts || []) {
      availableLocationSet.add(product.city);
    }

    return Array.from(availableLocationSet);
  }, [unfilteredProducts]);

  const maximumPrice = useMemo(
    () =>
      unfilteredProducts?.reduce((max, next) => {
        return Math.max(max, next.price);
      }, 0) || 1000,
    [unfilteredProducts],
  );

  if (isLoading) {
    return <Spin />;
  }

  if (error) {
    return (
      <ErrorPage
        message={
          (error as {message?: string})?.message || JSON.stringify(error)
        }
      />
    );
  }

  return (
    <div className={searchStyle.container}>
      <div className={searchStyle['filters-container']}>
        <h6 className="text-sm font-bold">Filters</h6>

        <div className={searchStyle['filter-box']}>
          <div className={searchStyle['filter-label']}>Category: </div>
          <Select
            className="w-full"
            mode="tags"
            value={filter.categories || []}
            options={availableCategories.map((category) => ({
              value: category,
              label: category,
            }))}
            onChange={(categories) => {
              setSearch((prevSearch) =>
                filterToRawSearch(prevSearch, {...filter, categories}),
              );
            }}
          />
        </div>

        <div className={searchStyle['filter-box']}>
          <div className={searchStyle['filter-label']}>Price ($): </div>
          <Slider
            range
            value={[
              filter?.rentRange?.[0] || 0,
              filter?.rentRange?.[1] || maximumPrice,
            ]}
            min={0}
            max={maximumPrice}
            step={50}
            marks={{
              0: '0$',
              [maximumPrice]: `${maximumPrice}$`,
            }}
            onChange={(range) => {
              setSearch((prevSearch) =>
                filterToRawSearch(prevSearch, {...filter, rentRange: range}),
              );
            }}
          />
        </div>

        <div className={searchStyle['filter-box']}>
          <div className={searchStyle['filter-label']}>Location: </div>
          <Select
            className="w-full"
            mode="tags"
            value={filter.location || []}
            options={availableLocation.map((location) => ({
              value: location,
              label: location,
            }))}
            onChange={(locations) => {
              setSearch((prevSearch) =>
                filterToRawSearch(prevSearch, {...filter, location: locations}),
              );
            }}
          />
        </div>

        <div className={searchStyle['filter-box']}>
          <div className={searchStyle['filter-label']}>Sort by: </div>
          <Select
            className="w-full"
            value={filter.sortBy}
            options={[
              SORT_BY.NONE,
              SORT_BY.PRICE_HIGH_TO_LOW,
              SORT_BY.PRICE_LOW_TO_HIGH,
            ].map((sort) => ({
              value: sort,
              label: getSortByLabel(sort),
            }))}
            onChange={(value) => {
              setSearch((prevSearch) =>
                filterToRawSearch(prevSearch, {...filter, sortBy: value}),
              );
            }}
          />
        </div>
      </div>
      <div className={searchStyle['products-container']}>
        <Products products={products || []} />
      </div>
    </div>
  );
};
