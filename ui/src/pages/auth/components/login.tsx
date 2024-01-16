import {GoogleLogin} from '@react-oauth/google';
import {useMutation} from 'react-query';
import {useForm, Controller} from 'react-hook-form';
import {yupResolver} from '@hookform/resolvers/yup';
import {loginSchema, setAuthTypeInSearch} from '@src/pages/auth/utils';
import commonStyles from './common.module.less';
import Input from 'antd/es/input/Input';
import {Button} from 'antd';
import Link from 'antd/es/typography/Link';
import {useNavigate, useSearchParams} from 'react-router-dom';
import {CredentialsDto, UserDto} from '@src/lib/api-schemas';
import {customFetch} from '@src/queries/base';
import {AUTH_SESSION_TOKEN_KEY} from '@src/lib/constants';
import {useUserContext} from '@src/lib/store/user/consumer';

export const Login = () => {
  const [, setSearch] = useSearchParams();
  const navigate = useNavigate();

  const {setUser} = useUserContext();

  const {control, handleSubmit, formState} = useForm({
    resolver: yupResolver(loginSchema),
  });

  const {mutate: login, isLoading: loggingIn} = useMutation(
    (payload: CredentialsDto) =>
      customFetch(
        'login',
        {body: JSON.stringify(payload), method: 'POST'},
        {ignoreAuthToken: true},
      )
        .then((res: Response) => res.json())
        .then((res: UserDto) => {
          if (!res.login) {
            return;
          }

          // set token
          if (res.token && res.token !== '') {
            sessionStorage.setItem(AUTH_SESSION_TOKEN_KEY, res.token);
          }

          setUser(res);
          navigate('/');
        }),
  );

  const onLogin = handleSubmit((data) => {
    login({login: data.emailOrContact, password: data.password});
  });

  return (
    <div className={commonStyles['widget-box']}>
      <h5>Login</h5>

      <div className={commonStyles.form}>
        <div>
          <Controller
            name="emailOrContact"
            control={control}
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="johndoe@gmail.com"
              />
            )}
          />

          {formState.errors.emailOrContact && (
            <i className="text-red-500 text-xs">
              {formState.errors.emailOrContact?.message}
            </i>
          )}
        </div>

        <div>
          <Controller
            control={control}
            name="password"
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="password"
                type="password"
              />
            )}
          />

          {formState.errors.password && (
            <i className="text-red-500 text-xs">
              {formState.errors.password?.message}
            </i>
          )}
        </div>
      </div>

      <Button
        loading={loggingIn}
        type="primary"
        className="mt-6"
        onClick={() => {
          onLogin();
        }}
      >
        Login
      </Button>

      <p className="text-center font-bold text-xs text-gray-500 my-5">or</p>

      <GoogleLogin onSuccess={(res) => console.log(res)} />

      <Link
        onClick={() =>
          setSearch((search) => {
            return setAuthTypeInSearch(search, 'signup');
          })
        }
      >
        <div className="text-center mt-7">Don't have an account? Register</div>
      </Link>
    </div>
  );
};
