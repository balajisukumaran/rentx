import {TReview} from '@src/lib/api-schemas';
import {queries} from '@src/queries';
import {customFetch} from '@src/queries/base';
import {queryClient} from '@src/queries/client';
import {useMutation} from 'react-query';

export const usePostReview = () =>
  useMutation(
    ({productId, review}: {productId: string; review: Partial<TReview>}) =>
      customFetch(`api/products/review/${productId}`, {
        method: 'POST',
        body: JSON.stringify(review),
      }).then(() =>
        queryClient.invalidateQueries(
          queries.product.review(productId).queryKey,
        ),
      ),
  );
