// STATIC TEMPORARY DATA
import {
  faBed,
  faBook,
  faDollar,
  faLeaf,
  faMobile,
  faSmile,
  faSpoon,
  faUserGroup,
} from '@fortawesome/free-solid-svg-icons';
import {ProductDto} from './lib/api-schemas';

export const images = [
  'https://images.unsplash.com/photo-1530263503756-b382295fd927?auto=format&fit=crop&q=80&w=2940&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1640003145169-d10a2e85a64d?auto=format&fit=crop&q=80&w=2787&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1598965402089-897ce52e8355?auto=format&fit=crop&q=80&w=2836&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1518444065439-e933c06ce9cd?auto=format&fit=crop&q=80&w=2874&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
];

export const getRandomImages = (
  min: number = 0,
  max: number = products.length - 1,
) => {
  min = Math.ceil(min);
  max = Math.floor(max);
  const idx = Math.floor(Math.random() * (max - min + 1)) + min;

  return products[idx]?.images;
};

export const categories = [
  {
    name: 'Electronic',
    icon: faMobile,
  },
  {
    name: 'Kitchen Appliances',
    icon: faSpoon,
  },
  {
    name: 'Furniture',
    icon: faBed,
  },
  {
    name: 'Books',
    icon: faBook,
  },
];

export const products: Partial<ProductDto>[] = [
  {
    productID: '1',
    city: 'Toronto',
    productName: 'Hutch Metal Queen Hydraulic Storage Bed',
    categoryName: 'Furniture',
    price: 10,
    images: [
      'https://p.rmjo.in/moodShot/vyi06e6u-1024x512.jpg',
      'https://p.rmjo.in/productSquare/7i306dfx-500x500.jpg',
      'https://p.rmjo.in/productSquare/phhpbdlz-500x500.jpg',
    ],
    productDescription: `Comfort, elegance & storage—get them all with Hutch Storage bed. The bed's sturdy metal frame ensures durability, while the honey-toned solid wood adds a touch of elegant styling, and the cushioned headboard provides comfort unlike any other.


    Dimensions
    76.5"L x 62"B x 35.5"H
    
    Material & Color
    Metal & Solid Wood
    Features & Specs
    Honey brown finish
    Ideal mattress size (L x B): 72" x 60"
    Sturdy metal frame
    Leathered headboard
    Anti-rust, durable coating`,
    postDate: new Date().getMilliseconds(),
    firstName: 'Mayursinh',
    lastName: 'Sarvaiya',
  },
  {
    productID: '2',
    city: 'Halifax',
    productName: 'Single Door Fridge (190 Litre)',
    price: 5,
    categoryName: 'Appliances',
    images: [
      'https://p.rmjo.in/moodShot/l0hefedy-1024x512.jpg',
      'https://p.rmjo.in/productSquare/2uurjwiq-500x500.jpg',
      'https://p.rmjo.in/productSquare/7muqhcqr-500x500.jpg',
    ],
    postDate: new Date().getMilliseconds(),
    firstName: 'Mayursinh',
    lastName: 'Sarvaiya',
  },
  {
    productID: '3',
    city: 'Halifax',
    productName: 'Galaxy Note 8 (Black)',
    categoryName: 'Electronic',
    price: 12,
    images: [
      'https://p.rmjo.in/moodShot/pqa11dle-1024x512.jpg',
      'https://p.rmjo.in/productSquare/xt7t4ovo-500x500.jpg',
      'https://p.rmjo.in/productSquare/x6cwl6qg-500x500.jpg',
    ],
    postDate: new Date().getMilliseconds(),
    firstName: 'Mayursinh',
    lastName: 'Sarvaiya',
  },
  {
    productID: '4',
    city: 'Halifax',
    productName: 'Dishwasher',
    categoryName: 'Appliances',
    price: 3,
    images: [
      'https://p.rmjo.in/moodShot/97g15v8f-1024x512.jpg',
      'https://p.rmjo.in/productSquare/0qz8ijp2-500x500.jpg',
      'https://p.rmjo.in/productSquare/sil0u4fm-500x500.jpg',
    ],
    postDate: new Date().getMilliseconds(),
    firstName: 'Mayursinh',
    lastName: 'Sarvaiya',
  },
  {
    productID: '5',
    city: 'Halifax',
    productName: 'Dishwasher New',
    categoryName: 'Appliances',
    price: 5,
    images: [
      'https://p.rmjo.in/moodShot/97g15v8f-1024x512.jpg',
      'https://p.rmjo.in/productSquare/0qz8ijp2-500x500.jpg',
      'https://p.rmjo.in/productSquare/sil0u4fm-500x500.jpg',
    ],
    postDate: new Date().getMilliseconds(),
    firstName: 'Mayursinh',
    lastName: 'Sarvaiya',
  },
  {
    productID: '6',
    city: 'Halifax',
    productName: 'Dishwasher Newest',
    categoryName: 'Appliances',
    price: 8,
    images: [
      'https://p.rmjo.in/moodShot/97g15v8f-1024x512.jpg',
      'https://p.rmjo.in/productSquare/0qz8ijp2-500x500.jpg',
      'https://p.rmjo.in/productSquare/sil0u4fm-500x500.jpg',
    ],
    postDate: new Date().getMilliseconds(),
    firstName: 'Mayursinh',
    lastName: 'Sarvaiya',
  },
];

export const messaging = [
  {
    title: 'Sustainable Consumption',
    description: `Renting products instead of buying them helps reduce waste and lowers your carbon footprint.`,
    icon: faLeaf,
  },
  {
    title: 'Cost-Efficiency',
    description:
      'Renting allows you to access high-quality items without the hefty price tag.',
    icon: faDollar,
  },
  {
    title: 'Variety and Flexibility',
    description:
      'With our vast selection of products, you can experiment with different options to find what suits you best.',
    icon: faSmile,
  },
  {
    title: 'Community Sharing',
    description:
      'Our platform fosters a community spirit. Renting products encourages sharing and collaboration, allowing you to connect with like-minded individuals who share your interests.',
    icon: faUserGroup,
  },
];

export const testimonials = [
  {
    user: 'Emily R.',
    testimonial: `Renting on this platform has not only saved me money but also allowed me to be more eco-conscious. I'm proud to be part of the solution. Highly recommended!`,
    avatar: 'https://i.pravatar.cc/150?img=49',
  },
  {
    user: 'James W.',
    testimonial: `The convenience and variety of products available for rent here are amazing. I've found everything from power tools to party supplies. It's like having an endless closet of useful things without the clutter.`,
    avatar: 'https://i.pravatar.cc/150?img=68',
  },
];

export const area = [
  {
    city: 'Toronto',
  },
  {
    city: 'Alberta',
  },
  {
    city: 'Halifax',
  },
];
