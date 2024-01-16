import {UserDto} from '@src/lib/api-schemas';
import {createContext} from 'react';

export type TUserContext = {
  user?: Pick<
    UserDto,
    | 'firstName'
    | 'lastName'
    | 'phone'
    | 'login'
    | 'userID'
    | 'prefLanguage'
    | 'status'
  >;
  setUser: (user: TUserContext['user']) => void;
  userLoading?: boolean;
  address?: string;
  setAddress: (address: string) => void;
};

export const UserContext = createContext({} as TUserContext);
