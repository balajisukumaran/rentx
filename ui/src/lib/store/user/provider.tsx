import {ReactNode, useEffect, useRef, useState} from 'react';
import {TUserContext, UserContext} from './context';
import {AUTH_SESSION_TOKEN_KEY} from '@src/lib/constants';
import {useQuery} from 'react-query';
import {queries} from '@src/queries';
import {Spin} from 'antd';
import {useNavigate} from 'react-router-dom';
import {GeocodingResponse} from '@src/lib/types';

export const UserContextProvider = ({children}: {children?: ReactNode}) => {
  const [user, setUser] = useState<TUserContext['user']>();
  const navigate = useNavigate();

  const token = useRef(sessionStorage.getItem(AUTH_SESSION_TOKEN_KEY) || '');

  const [address, setAddress] = useState<string>();
  useEffect(() => {
    navigator.geolocation.getCurrentPosition((position) => {
      const {
        coords: {latitude, longitude},
      } = position;

      // toronto latitude longitude
      // latitude = 43.6532;
      // longitude = -79.3832;

      fetch(`https://geocode.maps.co/reverse?lat=${latitude}&lon=${longitude}`)
        .then((res) => res.json())
        .then((address: GeocodingResponse) =>
          setAddress(address?.address?.city),
        );
    });
  }, []);

  const {
    data: userData,
    isLoading: isUserdataLoading,
    error,
  } = useQuery({
    ...queries.user.getByToken(token.current),
    enabled: Boolean(token.current) && token.current !== '',
  });

  useEffect(() => {
    setUser(userData);
  }, [userData]);

  useEffect(() => {
    if (error) {
      sessionStorage.removeItem(AUTH_SESSION_TOKEN_KEY);
      navigate('/auth?type=login');
    }
  }, [error, navigate]);

  // useEffect(() => {
  //   navigate(initialPath.current);
  //   initialPath.current = '/';
  // }, [user, navigate]);

  return (
    <UserContext.Provider
      value={{
        user,
        setUser,
        userLoading: isUserdataLoading,
        address,
        setAddress,
      }}
    >
      {!isUserdataLoading && children}
      {isUserdataLoading && (
        <div className="h-screen w-screen flex items-center justify-center">
          <Spin size="large" />
        </div>
      )}
    </UserContext.Provider>
  );
};
