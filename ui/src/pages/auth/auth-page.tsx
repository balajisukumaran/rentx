import SharingIllustration from '@src/assets/sharing-illustration.svg';
import ForgetPasswordIllustration from '@src/assets/forget-password-illustration.svg';
import ResetPasswordIllustration from '@src/assets/reset-password-illustration.svg';
import classNames from 'classnames';
import authStyles from './auth.module.less';
import {Outlet} from 'react-router-dom';

export const AuthenticationPage = () => {
  const path = window.location.pathname;

  let src = SharingIllustration;

  if (path?.includes('forget-password')) {
    src = ForgetPasswordIllustration;
  } else if (path?.includes('reset-password')) {
    src = ResetPasswordIllustration;
  }

  return (
    <div className={classNames(authStyles.screen)}>
      <div className={classNames('w-1/2', authStyles['marketing-section'])}>
        <div>
          <h1>RentX</h1>
          <span>why buy, when you can rent.</span>
        </div>
        <img className={authStyles.illustration} src={src} />
      </div>

      <div
        className={classNames(
          'w-9/12 lg:w-1/2 mx-auto',
          authStyles['auth-section'],
        )}
      >
        <Outlet />
      </div>
    </div>
  );
};
