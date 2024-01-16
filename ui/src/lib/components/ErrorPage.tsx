import ErrorPageIllustration from '@src/assets/error.svg';

type ErrorPageProps = {
  message: string;
};

export const ErrorPage = ({message}: ErrorPageProps) => {
  return (
    <div className="flex flex-col gap-5 text-center">
      <img src={ErrorPageIllustration} className="h-56" />
      <h6>Ops something is wrong</h6>
      <p>{message}</p>
    </div>
  );
};
