import {GoogleLogin} from '@react-oauth/google';
import {useMutation} from 'react-query';
import {useForm, Controller} from 'react-hook-form';
import {yupResolver} from '@hookform/resolvers/yup';
import {registerSchema, setAuthTypeInSearch} from '@src/pages/auth/utils';
import commonStyles from './common.module.less';
import Input from 'antd/es/input/Input';
import {Button} from 'antd';
import Link from 'antd/es/typography/Link';
import {useNavigate, useSearchParams} from 'react-router-dom';
import {SignUpDto, UserDto} from '@src/lib/api-schemas';
import {customFetch} from '@src/queries/base';
import {AUTH_SESSION_TOKEN_KEY} from '@src/lib/constants';
import {useUserContext} from '@src/lib/store/user/consumer';

export const Register = () => {
  const [, setSearch] = useSearchParams();
  const navigate = useNavigate();

  const {setUser} = useUserContext();

  const {control, handleSubmit, formState} = useForm({
    resolver: yupResolver(registerSchema),
  });

  const {mutate: register, isLoading: registering} = useMutation(
    (payload: SignUpDto) =>
      customFetch(
        'register',
        {
          body: JSON.stringify(payload),
          method: 'POST',
        },
        {ignoreAuthToken: true},
      )
        .then((res: Response) => {
          return res.json();
        })
        .then((res: UserDto) => {
          // set token
          if (res.token && res.token !== '') {
            sessionStorage.setItem(AUTH_SESSION_TOKEN_KEY, res.token);
          }

          setUser(res);
          navigate('/');
        }),
  );

  const onRegister = handleSubmit((data) => {
    const nameSplit = data.fullName.split(' ');

    const firstName = nameSplit.slice(0, nameSplit.length - 1).join(' ');

    const lastName = nameSplit.length > 1 ? nameSplit.slice(-1)[0] : '';

    register({
      login: data.email,
      email: data.email,
      phone: data.contactNo,
      password: data.password,
      firstName,
      lastName,
      areaId: '503',
      joiningDate: new Date(),
      prefLanguage: 'english',
      privacy: 'enabled',
      status: 'verified',
    });
  });

  return (
    <div className={commonStyles['widget-box']}>
      <h5>Register</h5>

      <div className={commonStyles.form}>
        <div>
          <Controller
            name="fullName"
            control={control}
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="Full name"
              />
            )}
          />
          {formState.errors.fullName && (
            <i className="text-red-500 text-xs">
              {formState.errors.fullName?.message}
            </i>
          )}
        </div>
        <div>
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

        <div>
          <Controller
            control={control}
            name="contactNo"
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="contact number"
              />
            )}
          />

          {formState.errors.contactNo && (
            <i className="text-red-500 text-xs">
              {formState.errors.contactNo?.message}
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
        loading={registering}
        type="primary"
        className="mt-6"
        onClick={() => {
          onRegister();
        }}
      >
        Register
      </Button>

      <p className="text-center font-bold text-xs text-gray-500 my-5">or</p>

      <GoogleLogin onSuccess={(res) => console.log(res)} />

      <Link
        onClick={() =>
          setSearch((search) => {
            return setAuthTypeInSearch(search, 'login');
          })
        }
      >
        <div className="text-center mt-7">Already have an account? Login</div>
      </Link>
    </div>
  );
};
