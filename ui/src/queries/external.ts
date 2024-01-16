import {createQueryKeys} from '@lukemorales/query-key-factory';

export const external = createQueryKeys('external', {
  listLanguages: () => ({
    queryKey: [''],
    queryFn: (): Promise<string[]> =>
      fetch(
        'https://gist.githubusercontent.com/josantonius/b455e315bc7f790d14b136d61d9ae469/raw/09dfea834a8f92865b52d017e2f91ca4b55b0ef9/language-codes.json',
      )
        .then((res) => res.json())
        .then((res) => Object.values(res)),
  }),
});
