import * as yup from 'yup';

export const addProductSchema = yup.object({
  name: yup.string().required(),
  description: yup.string().required(),
  price: yup.number().required(),
  area: yup
    .object({
      areaID: yup.number().optional(),
      city: yup.string().required(),
    })
    .required(),
  category: yup
    .object({
      categoryID: yup.number().optional(),
      name: yup.string().required(),
    })
    .required(),
  sellType: yup.string().optional(),

  manufacture: yup.string().optional(),
  year_of_purchase: yup.string().optional(),
  model_name: yup.string().optional(),
  gadget_type: yup.string().optional(),

  furniture_type: yup.string().optional(),
  condition: yup.string().optional(),

  author: yup.string().optional(),
  book_condition: yup.string().optional(),
  year_of_public: yup.string().optional(),

  appliance_type: yup.string().optional(),
});
