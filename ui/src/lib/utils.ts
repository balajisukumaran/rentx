import {getRandomImages} from '@src/temp';
import {ImageDto, ProductDto, ProductReal} from './api-schemas';
import {TUserContext} from './store/user/context';

export const isUserVerifiedSeller = (user: TUserContext['user']) =>
  user?.status?.toLowerCase() === 'verified';

export const isUserUnverifiedSeller = (user: TUserContext['user']) =>
  user?.status?.toLowerCase() === 'unverified';

export const isUserSeller = (user: TUserContext['user']) =>
  isUserVerifiedSeller(user) || isUserUnverifiedSeller(user);

export const userAsVerifiedSeller = (user: TUserContext['user']) => ({
  ...user,
  status: 'verified',
});

export const userAsUnverifiedSeller = (user: TUserContext['user']) => ({
  ...user,
  status: 'unverified',
});

export const mapProductRealToDTO = (
  product: ProductReal,
): Partial<ProductDto> => ({
  productID: product.productID,
  productName: product.name,
  productDescription: product.description,
  price: +product.price,
  areaId: +product?.area?.areaID,
  categoryId: +product?.category?.categoryID,
  city: product?.area?.city,
  images: getRandomImages(+product.productID),
});

export const mapDTOToProductReal = (
  product: Partial<ProductDto>,
): Partial<ProductReal> => ({
  productID: product?.productID,
  name: product?.productName,
  description: product?.productDescription,
  price: `${product?.price}`,
  area: {
    areaID: `${product?.areaId}` || '',
    areaName: product?.areaName || '',
    city: product?.city || '',
    country: product?.country || '',
    state: product?.state || '',
  },
  category: {
    categoryID: `${product?.categoryId}` || '',
    name: product?.categoryName || '',
  },
});

export const withFirstLetterUppercase = (str: string) =>
  `${str[0].toUpperCase()}${str.slice(1)}`;

export const getImageSrc = (image: ImageDto) =>
  `${__API_HOST__}/api/image/download?fileName=${image?.name}`;
