import {useForm, Controller} from 'react-hook-form';
import {resetPasswordSchema} from '@src/pages/auth/utils';
import Input from 'antd/es/input/Input';
import {yupResolver} from '@hookform/resolvers/yup';
import commonStyles from './common.module.less';
import {Button, notification} from 'antd';
import {ResetPasswordSchema} from '../types';
import {useNavigate, useSearchParams} from 'react-router-dom';
import {useMutation} from 'react-query';
import {NewPasswordDto} from '@src/lib/api-schemas';
import {customFetch} from '@src/queries/base';

export const ResetPassword = () => {
  const [search] = useSearchParams();
  const navigate = useNavigate();

  const {control, handleSubmit, formState} = useForm<ResetPasswordSchema>({
    resolver: yupResolver(resetPasswordSchema),
  });

  const {mutate: resetPassword, isLoading} = useMutation(
    (newPasswordDto: NewPasswordDto) =>
      customFetch('new-password', {
        body: JSON.stringify(newPasswordDto),
        method: 'POST',
      }).then(() => {
        notification.success({
          message: 'Successfully updated password.',
          placement: 'bottomRight',
        });
        navigate('/auth?type=login');
      }),
  );

  const resetToken = search.get('token');

  const onResetPassword = handleSubmit((data) => {
    resetPassword({
      resetToken: resetToken as string,
      newPassword: data.confirmPassword,
    });
  });

  if (!resetToken || resetToken === '') {
    return (
      <div className={commonStyles['widget-box']}>
        <span className="text-red-500">
          Invalid reset password link. Click{' '}
          <a
            className="text-sky-500 cursor-pointer"
            onClick={() => navigate('/auth/forget-password')}
          >
            here
          </a>{' '}
          to resend link
        </span>
      </div>
    );
  }

  return (
    <div className={commonStyles['widget-box']}>
      <h5>Reset Password</h5>

      <div className={commonStyles.form}>
        <div>
          <Controller
            name="newPassword"
            control={control}
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="new password"
                type="password"
              />
            )}
          />

          {formState.errors.newPassword && (
            <i className="text-red-500 text-xs">
              {formState.errors.newPassword?.message}
            </i>
          )}
        </div>

        <div>
          <Controller
            name="confirmPassword"
            control={control}
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="confirm new password"
                type="password"
              />
            )}
          />

          {formState.errors.confirmPassword && (
            <i className="text-red-500 text-xs">
              {formState.errors.confirmPassword?.message}
            </i>
          )}
        </div>
      </div>
      <Button
        loading={isLoading}
        type="primary"
        className="mt-6"
        onClick={() => {
          onResetPassword();
        }}
      >
        Submit
      </Button>
    </div>
  );
};
