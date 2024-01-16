import * as yup from 'yup';
import {LoginSchema, RegisterSchema, ResetPasswordSchema} from './types';

export const phoneNumberRegex =
  /^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/;

const passwordValidation = yup
  .string()
  .required('No password provided')
  .min(8, 'Password should be minimum 8 characters.');

export const registerSchema: yup.ObjectSchema<RegisterSchema> = yup.object({
  email: yup.string().email().required('No email provided.'),
  fullName: yup.string().required('Full name required'),
  contactNo: yup
    .string()
    .matches(phoneNumberRegex, 'Invalid contact number.')
    .required('No contact number provided'),
  password: passwordValidation,
});

export type authType = 'signup' | 'login';

export const getAuthTypeFromSearch = (search: URLSearchParams): authType => {
  if (!search.get('type') || search.get('type') === 'signup') {
    return 'signup';
  }

  return 'login';
};

export const setAuthTypeInSearch = (
  search: URLSearchParams,
  type: authType,
) => {
  search.set('type', type);
  return search;
};

export const loginSchema: yup.ObjectSchema<LoginSchema> = yup.object({
  emailOrContact: yup

    .string()

    .required('No registered email or contact number provided'),

  password: yup.string().required('No password provided.'),
});

export const forgotPasswordSchema = yup.object({
  email: yup.string().email('Invalid email').required('No email provided'),
});

export const resetPasswordSchema: yup.ObjectSchema<ResetPasswordSchema> =
  yup.object({
    newPassword: passwordValidation,
    confirmPassword: passwordValidation.oneOf(
      [yup.ref('newPassword')],
      'Passwords do not match',
    ),
  });
