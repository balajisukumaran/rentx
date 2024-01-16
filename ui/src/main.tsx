import React from 'react';
import {loadStripe} from '@stripe/stripe-js';
import {Elements} from '@stripe/react-stripe-js';
import {QueryClientProvider} from 'react-query';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Outlet,
  Route,
  RouterProvider,
} from 'react-router-dom';
import {GoogleOAuthProvider} from '@react-oauth/google';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.less';
import {AuthenticationPage} from '@src/pages/auth/auth-page';
import {ForgotPassword} from '@src/pages/auth/components/forgot-password';
import {ResetPassword} from '@src/pages/auth/components/reset-password';
import {Authentication} from '@src/pages/auth/components/auth';
import {queryClient} from '@src/queries/client';
import {UserContextProvider} from '@src/lib/store/user/provider';
import {Layout} from '@src/pages/dashboard/components/layout';
import {SettingsPage} from '@src/pages/settings/settings-page';
import {ProductPage} from './pages/product/product-page';
import {SearchPage} from './pages/search/search-page';
import {WishlistPage} from './pages/wishlist/wishlist-page';
import {SellerPage} from './pages/seller/seller-page';
import {AddProduct} from './pages/seller/add-product';
import {EditProduct} from './pages/seller/edit-product';
import {STRIPE_PUBLISHABLE_KEY} from './lib/constants';
import {OrderPage} from './pages/orders/order-page';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route
      element={
        <UserContextProvider>
          <Outlet />
        </UserContextProvider>
      }
    >
      <Route path="/" element={<App />} />
      <Route path="/auth" element={<AuthenticationPage />}>
        <Route index element={<Authentication />} />
        <Route path="forget-password" element={<ForgotPassword />} />
        <Route path="reset-password" element={<ResetPassword />} />
      </Route>
      <Route
        element={
          <Layout>
            <Outlet />
          </Layout>
        }
      >
        <Route path="/product/:id" element={<ProductPage />} />
        <Route path="/search" element={<SearchPage />} />
      </Route>
      <Route
        element={
          <Layout authProtected>
            <Outlet />
          </Layout>
        }
      >
        <Route path="/settings" element={<SettingsPage />} />
        <Route path="/wishlist" element={<WishlistPage />} />
        <Route path="/seller" element={<SellerPage />} />
        <Route path="/product/add" element={<AddProduct />} />
        <Route path="/product/:id/edit" element={<EditProduct />} />
        <Route path="/orders" element={<OrderPage />} />
      </Route>
    </Route>,
  ),
);

const stripePromise = loadStripe(STRIPE_PUBLISHABLE_KEY);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Elements stripe={stripePromise}>
      <QueryClientProvider client={queryClient}>
        <GoogleOAuthProvider clientId="">
          <RouterProvider router={router} />
        </GoogleOAuthProvider>
      </QueryClientProvider>
    </Elements>
  </React.StrictMode>,
);
