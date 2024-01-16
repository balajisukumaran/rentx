import * as yup from 'yup';
import {UserSettingsSchema} from './types';
import {phoneNumberRegex} from '@src/pages/auth/utils';
import {RcFile} from 'antd/es/upload';

export const userSettingsSchema = yup.object<
  yup.ObjectSchema<UserSettingsSchema>
>({
  phone: yup
    .string()
    .matches(phoneNumberRegex, 'Invalid contact number')
    .required('No contact number provided'),
  firstName: yup.string().required(),
  lastName: yup.string().required(),
  prefLanguage: yup.string().required(),
});

export const getBase64 = (file: RcFile): Promise<string> =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result as string);
    reader.onerror = (error) => reject(error);
  });
