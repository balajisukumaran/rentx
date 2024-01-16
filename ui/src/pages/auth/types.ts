export type RegisterSchema = {
  email: string;
  fullName: string;
  contactNo: string;
  password: string;
};

export type LoginSchema = {
  emailOrContact: string;
  password: string;
};

export type ResetPasswordSchema = {
  newPassword: string;
  confirmPassword: string;
};
