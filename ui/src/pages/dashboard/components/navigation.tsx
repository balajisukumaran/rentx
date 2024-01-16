import {
  faBoxesPacking,
  faCog,
  faSearch,
  faStore,
} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {useNavigate} from 'react-router-dom';
import navigationStyles from './navigation.module.less';
import {Avatar, Button, Dropdown, Select} from 'antd';
import {useUserContext} from '@src/lib/store/user/consumer';
import {AUTH_SESSION_TOKEN_KEY} from '@src/lib/constants';
import {useState} from 'react';
import {area} from '@src/temp';
import {useQuery} from 'react-query';
import {queries} from '@src/queries';
import {faHeart} from '@fortawesome/free-regular-svg-icons';
import {getImageSrc, isUserSeller} from '@src/lib/utils';
import {ImageDto} from '@src/lib/api-schemas';

export const Navigation = () => {
  const {user, address, setAddress} = useUserContext();
  const navigate = useNavigate();

  let AuthView = null;

  const [search, setSearch] = useState('');

  const {data: products = []} = useQuery(queries.product.list());

  const {data: image} = useQuery({
    ...queries.user.image(user?.userID + ''),
    enabled: !!user?.userID,
  });

  const searchItems =
    search !== ''
      ? products
          .filter(
            (prod) =>
              JSON.stringify(prod)
                ?.toLowerCase()
                .includes(search?.toLowerCase()),
          )
          .slice(0, 5)
      : [];

  if (user?.login) {
    AuthView = (
      <Dropdown
        menu={{
          items: [
            {
              key: 'settings',
              label: (
                <span onClick={() => navigate('/settings')}>
                  <FontAwesomeIcon icon={faCog} className="mr-3" />
                  Settings
                </span>
              ),
            },
            {
              key: 'orders',
              label: (
                <span onClick={() => navigate('/orders')}>
                  <FontAwesomeIcon icon={faBoxesPacking} className="mr-3" />
                  Orders
                </span>
              ),
            },
            {
              key: 'wishlist',
              label: (
                <span onClick={() => navigate('/wishlist')}>
                  <FontAwesomeIcon icon={faHeart} className="mr-3" />
                  Wishlist
                </span>
              ),
            },
            {
              key: 'seller',
              label: (
                <span onClick={() => navigate('/seller')}>
                  <FontAwesomeIcon icon={faStore} className="mr-3" />
                  {isUserSeller(user) ? 'Seller' : 'Become a Seller'}
                </span>
              ),
            },
            {
              key: 'logout',
              label: (
                <span
                  onClick={() => {
                    sessionStorage.removeItem(AUTH_SESSION_TOKEN_KEY);
                    navigate('/auth?type=login');
                  }}
                  className="text-red-500"
                >
                  Logout
                </span>
              ),
            },
          ],
        }}
      >
        <Avatar className="cursor-pointer" src={getImageSrc(image as ImageDto)}>
          {user.firstName[0]}
        </Avatar>
      </Dropdown>
    );
  } else {
    AuthView = (
      <Button
        onClick={() => {
          navigate('/auth?type=login');
        }}
        className="ant-btn-primary-inverse font-light"
      >
        Login
      </Button>
    );
  }

  return (
    <div className={navigationStyles.container}>
      <div className={navigationStyles.wrapper}>
        <h6 className="cursor-pointer" onClick={() => navigate('/')}>
          RentX
        </h6>

        <Select
          labelInValue
          filterOption={false}
          onSearch={setSearch}
          options={searchItems.map((item) => ({
            label: (
              <div
                onClick={() => navigate(`/product/${item.productID}`)}
                className="flex gap-4 items-center"
              >
                <div>
                  <p className="text-sm">{item.productName}</p>
                  <p className="text-xs font-light text-gray-500">
                    {item.categoryName}
                  </p>
                </div>
              </div>
            ),
          }))}
          mode="multiple"
          placeholder="Search items"
          rootClassName="w-6/12"
          suffixIcon={<FontAwesomeIcon icon={faSearch} />}
          onKeyUp={(e) => {
            if (e.key === 'Enter') {
              navigate('/search');
            }
          }}
        />

        {!!address && (
          <Select
            className="w-2/12"
            value={address}
            options={area.map((data) => ({label: data.city, value: data.city}))}
            onChange={(value) => setAddress(value)}
          />
        )}

        <div className={navigationStyles['auth-view']}>{AuthView}</div>
      </div>
    </div>
  );
};
