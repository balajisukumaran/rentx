import {Register} from 'src/pages/auth/components/register';

import {useSearchParams} from 'react-router-dom';

import {ReactNode} from 'react';

import {getAuthTypeFromSearch} from '../utils';

import {Login} from 'src/pages/auth/components/login';

export const Authentication = () => {
  const [search] = useSearchParams();

  const type = getAuthTypeFromSearch(search);

  let AuthType: ReactNode = null;

  if (type === 'signup') {
    AuthType = <Register />;
  } else {
    AuthType = <Login />;
  }

  return AuthType;
};
