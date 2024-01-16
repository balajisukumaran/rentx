import {AUTH_SESSION_TOKEN_KEY, BASE_URL} from '@src/lib/constants';

export const customFetch = (
  endpoint: string,
  init: RequestInit | undefined = {},
  opts: {
    ignoreAuthToken?: boolean;
    ignoreRedirectOnFail?: boolean;
    noOverrideHeaders?: boolean;
    queryString?: string;
  } = {},
): Promise<Response> => {
  const url = new URL(BASE_URL);

  url.pathname = endpoint;
  url.search = opts?.queryString || '';

  const token = sessionStorage.getItem(AUTH_SESSION_TOKEN_KEY);

  const headers: RequestInit['headers'] = new Headers(init?.headers);
  if (
    !opts?.ignoreAuthToken &&
    token &&
    token !== '' &&
    token !== 'undefined'
  ) {
    headers.set('Authorization', `Bearer ${token}`);
  }

  if (!headers.get('content-type') && !opts?.noOverrideHeaders) {
    headers.set('content-type', 'application/json');
  }

  init.headers = headers;

  return fetch(url, init)
    .then((res) => {
      if (res.status === 401) {
        if (
          !opts?.ignoreRedirectOnFail &&
          !window.location.pathname.includes('auth')
        ) {
          // window.location.replace('/auth?type=login');
        }

        return res.json().then((res) => {
          throw new Error(JSON.stringify(res));
        });
      }

      return res;
    })
    .catch((err) => {
      throw err;
    });
};
