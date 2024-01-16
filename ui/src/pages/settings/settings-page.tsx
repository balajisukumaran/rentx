import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import settingsStyles from './settings.module.less';
import {faTrash, faUpload, faUser} from '@fortawesome/free-solid-svg-icons';
import {useUserContext} from '@src/lib/store/user/consumer';
import {
  Avatar,
  Button,
  Input,
  Popconfirm,
  Select,
  Upload,
  UploadFile,
  notification,
} from 'antd';
import {useState} from 'react';
import {Controller, useForm} from 'react-hook-form';
import {useMutation, useQuery} from 'react-query';
import {queries} from '@src/queries';
import {UserDto} from '@src/lib/api-schemas';
import {customFetch} from '@src/queries/base';
import {getBase64} from './utils';
import {RcFile} from 'antd/es/upload';
import {getImageSrc} from '@src/lib/utils';
import {queryClient} from '@src/queries/client';
import {useNavigate} from 'react-router-dom';

export const SettingsPage = () => {
  const navigate = useNavigate();
  const {user} = useUserContext();

  const {control, formState, handleSubmit} = useForm({
    defaultValues: user,
  });

  const [fileList, setFileList] = useState<UploadFile[]>([]);

  const {data: languages, isLoading: loadingLanguages} = useQuery(
    queries.external.listLanguages(),
  );

  const {mutate: updateProfile, isLoading: updatingProfile} = useMutation(
    (userDto: Partial<UserDto>) =>
      customFetch(
        `api/profile/${user?.userID}`,
        {
          method: 'PUT',
          body: JSON.stringify(userDto),
        },
        {ignoreRedirectOnFail: true},
      )
        .then(() => {
          notification.success({
            message: 'Profile updated successfully.',
            placement: 'bottomRight',
          });
        })
        .catch((err) => {
          notification.error({
            message: err?.message || JSON.stringify(err),
            placement: 'bottomRight',
          });
        }),
  );

  const {data: image} = useQuery({
    ...queries.user.image(user?.userID + ''),
    enabled: !!user?.userID,
  });

  const {mutateAsync: uploadPhoto} = useMutation(
    async ({image, id}: {image: UploadFile; id: string}) => {
      const formData = new FormData();
      formData.append('file', image.originFileObj as RcFile, `${id}-image`);
      formData.append('id', `${id}`);
      formData.append('type', 'user');

      await customFetch(
        `api/image/upload`,
        {method: 'POST', body: formData},
        {
          ignoreRedirectOnFail: true,
          noOverrideHeaders: true,
        },
      );
    },
  );

  const onUpdateProfile = handleSubmit((data) => updateProfile(data));

  const [previewURL, setPreviewURL] = useState('');

  const generatePreviewURL = async () => {
    if (fileList?.length > 0) {
      const preview = await getBase64(fileList[0]?.originFileObj as RcFile);

      setPreviewURL(preview);
      return;
    }
  };

  return (
    <div className={settingsStyles.container}>
      <h6>
        <FontAwesomeIcon icon={faUser} className="mr-5" />
        Update profile
      </h6>

      <div className={settingsStyles.body}>
        <div className={settingsStyles.section}>
          {fileList?.length === 0 && !image?.name && (
            <Avatar size="large">{user?.firstName[0]}</Avatar>
          )}
          {fileList?.length > 0 && (
            <img className={settingsStyles.profilePic} src={previewURL} />
          )}
          {image?.name && !fileList?.length && (
            <img
              className={settingsStyles.profilePic}
              src={getImageSrc(image)}
            />
          )}
        </div>

        <div className={settingsStyles.section}>
          <Upload
            fileList={fileList}
            maxCount={1}
            onChange={({fileList}) => {
              setFileList(fileList);
              generatePreviewURL();
              uploadPhoto({image: fileList[0], id: user?.userID + ''}).then(
                () => {
                  queryClient.invalidateQueries(
                    queries.user.image(user?.userID + ''),
                  );
                },
              );
            }}
            beforeUpload={() => false}
            accept="image/jpg,jpeg,png"
          >
            <Button icon={<FontAwesomeIcon icon={faUpload} />}>
              Upload new profile picture
            </Button>
          </Upload>
        </div>

        <div className={settingsStyles.section}>
          <label>First name: </label>
          <Controller
            control={control}
            name="firstName"
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="First name"
              />
            )}
          />
          {formState.errors.firstName && (
            <i className="text-red-500 text-xs">
              {formState.errors.firstName?.message}
            </i>
          )}
        </div>

        <div className={settingsStyles.section}>
          <label>Last name: </label>
          <Controller
            control={control}
            name="lastName"
            render={({field}) => (
              <Input
                value={field.value}
                onChange={field.onChange}
                placeholder="Last name"
              />
            )}
          />
          {formState.errors.lastName && (
            <i className="text-red-500 text-xs">
              {formState.errors.lastName?.message}
            </i>
          )}
        </div>

        <div className={settingsStyles.section}>
          <label>Contact no: </label>
          <Controller
            control={control}
            name="phone"
            render={({field}) => (
              <Input value={field.value} onChange={field.onChange} />
            )}
          />
          {formState.errors.phone && (
            <i className="text-red-500 text-xs">
              {formState.errors?.phone?.message}
            </i>
          )}
        </div>

        <div className={settingsStyles.section}>
          <label>Preferred language: </label>
          <Controller
            control={control}
            name="prefLanguage"
            render={({field}) => (
              <Select
                loading={loadingLanguages}
                showSearch
                placeholder="Select preferred language"
                value={field.value}
                options={languages?.map((lang) => ({value: lang, label: lang}))}
                className="w-full"
                onChange={(value) => field.onChange(value)}
              />
            )}
          />
        </div>

        <div className={settingsStyles.section}>
          <Button
            type="primary"
            loading={updatingProfile}
            onClick={() => onUpdateProfile()}
          >
            Save Changes
          </Button>
        </div>

        <div className={settingsStyles.section}>
          <Popconfirm
            title="Delete user account"
            onConfirm={() =>
              customFetch(`api/delete`, {method: 'DELETE'}).then(() => {
                navigate('/auth');
              })
            }
          >
            <Button
              danger
              icon={<FontAwesomeIcon icon={faTrash} />}
              size="large"
            >
              Delete Account
            </Button>
          </Popconfirm>
        </div>
      </div>
    </div>
  );
};
