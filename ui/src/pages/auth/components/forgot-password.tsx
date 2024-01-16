import {useForm, Controller} from 'react-hook-form';
import Input from 'antd/es/input/Input';
import {yupResolver} from '@hookform/resolvers/yup';
import commonStyles from './common.module.less';
import {Button, notification} from 'antd';
import {forgotPasswordSchema} from '@src/pages/auth/utils';
import {useMutation} from 'react-query';
import {ResetDto} from '@src/lib/api-schemas';
import {customFetch} from '@src/queries/base';

export const ForgotPassword = () => {
  const {control, handleSubmit, formState} = useForm({
    resolver: yupResolver(forgotPasswordSchema),
  });

  const {mutate: sendInvitationLink, isLoading: sendingLink} = useMutation(
    (payload: ResetDto) =>
      customFetch(
        'reset',
        {
          body: JSON.stringify(payload),
          method: 'POST',
        },
        {
          ignoreAuthToken: true,
        },
      ).then(() => {
        notification.success({
          message: 'Reset link sent successfully please check your email',
          placement: 'bottomRight',
        });
      }),
  );

  const onForgotPassword = handleSubmit((data) => {
    sendInvitationLink({flag: data.email});
  });

  return (
    <div className={commonStyles['widget-box']}>
      <h5>Forgot Password</h5>

      <div className={commonStyles.form}>
        <Controller
          name="email"
          control={control}
          render={({field}) => (
            <Input
              value={field.value}
              onChange={field.onChange}
              placeholder="johndoe@gmail.com"
            />
          )}
        />

        {formState.errors.email && (
          <i className="text-red-500 text-xs">
            {formState.errors.email?.message}
          </i>
        )}
      </div>
      <Button
        type="primary"
        className="mt-6"
        loading={sendingLink}
        onClick={() => {
          onForgotPassword();
        }}
      >
        Send Reset Link
      </Button>
    </div>
  );
};
