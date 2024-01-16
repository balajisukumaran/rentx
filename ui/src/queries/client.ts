import {notification} from 'antd';
import {MutationCache, QueryCache, QueryClient} from 'react-query';

const onError = (err: unknown) => {
  let errorMessage = 'Something went wrong';

  if ((err as Error).message) {
    errorMessage = (err as Error).message;
  }

  notification.error({
    message: errorMessage,
    placement: 'bottomRight',
  });
};

export const queryClient = new QueryClient({
  mutationCache: new MutationCache({onError}),
  queryCache: new QueryCache({onError}),
  defaultOptions: {
    queries: {
      retry: 0,
    },
  },
});
