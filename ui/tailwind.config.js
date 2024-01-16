/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{ts,tsx}'],
  theme: {
    fontFamily: {
      sans: ['Inter'],
    },
    extend: {
      colors: {
        blue: '#525FE1',
        orange: '#F86F03',
        'light-orange': '#FFA41B',
        'blue-faded': 'rgba(82, 95, 225, 0.4)',
        'faded-orange': 'rgba(248, 111, 3, 0.4)',
        platinum: '#E5E4E2',
      },
      boxShadow: {
        subtle: '0px 1px 4px 0px rgba(0, 0, 0, 0.16)',
        light: '0px 1px 4px 0px rgba(0, 0, 0, 0.05)',
      },
    },
  },
  plugins: [],
};
