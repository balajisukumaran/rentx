import {createQueryKeys} from '@lukemorales/query-key-factory';
import {customFetch} from './base';
import {ImageDto, JWTDto, PurchaseDto, UserDto} from '@src/lib/api-schemas';

export const user = createQueryKeys('user', {
  getByToken: (token: string) => ({
    queryKey: [''],
    queryFn: (): Promise<UserDto> => {
      const jwtDTO: JWTDto = {JWT: token};
      return customFetch(
        'user-by-token',
        {
          method: 'POST',
          body: JSON.stringify(jwtDTO),
        },
        {ignoreAuthToken: true},
      ).then((res) => res.json());
    },
  }),
  image: (userId: string) => ({
    queryKey: [userId],
    queryFn: () =>
      customFetch(
        `api/image/details`,
        {},
        {queryString: `?type=user&id=${userId}`, ignoreRedirectOnFail: true},
      )
        .then((res) => res.json())
        .then((imageDetails: ImageDto[]) =>
          imageDetails.find((i) => i.name === `${userId}-image`),
        )
        .catch(console.error),
  }),
  orders: () => ({
    queryKey: [''],
    queryFn: (): Promise<PurchaseDto[]> =>
      customFetch(`api/purchases`).then((res) => res.json()),
  }),
});
