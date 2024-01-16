import {ReactNode} from 'react';
import layoutStyles from './layout.module.less';
import {Navigation} from '@src/pages/dashboard/components/navigation';
import {useUserContext} from '@src/lib/store/user/consumer';

export const Layout = ({
  children,
  authProtected,
}: {
  children?: ReactNode;
  authProtected?: boolean;
}) => {
  const {user} = useUserContext();

  const Unauthorized = <h3 className="text-red-600">Unauthorized</h3>;

  const isUnauthorized = !user && authProtected;

  return (
    <div className={layoutStyles.container}>
      <Navigation />

      <section className={layoutStyles.body}>
        {isUnauthorized && Unauthorized}
        {!isUnauthorized && children}
      </section>
    </div>
  );
};
