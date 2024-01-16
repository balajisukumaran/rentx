import {ProductDto} from '@src/lib/api-schemas';
import {customFetch} from '@src/queries/base';
import {authHeader} from '@src/queries/payment';
import {useMutation} from 'react-query';

export const useCreateCustomer = () =>
  useMutation(
    (email: string): Promise<{id: string}> =>
      fetch('https://api.stripe.com/v1/customers', {
        method: 'POST',
        headers: {
          ...authHeader,
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
          email,
        }),
      }).then((res) => res.json()),
  );

export const useCreatePaymentIntent = () =>
  useMutation(
    ({
      data: {product, customerId, tokenId, address, price},
    }: {
      data: {
        product: ProductDto;
        customerId: string;
        tokenId: string;
        address: string;
        price: number;
      };
    }) => {
      return fetch('https://api.stripe.com/v1/payment_intents', {
        headers: {
          ...authHeader,
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        method: 'POST',
        body: new URLSearchParams({
          amount: `${Math.round(price * 100)}`,
          currency: 'cad',
          customer: customerId,
          'payment_method_data[type]': 'card',
          'payment_method_data[card][token]': tokenId,
          confirm: 'true',
          return_url: window.location.href,
          [`metadata[productId]`]: product?.productID,
          [`metadata[address]`]: address,
        }),
      }).then((res) => res.json());
    },
  );

export const usePurchaseProduct = () =>
  useMutation(
    (payload: {
      product: ProductDto;
      buyerId: string;
      pay: number;
      // 0 - sell
      // 1 - sell + emi
      // 2 - rent
      isEmi: boolean;
    }) => {
      let emi = payload.isEmi ? 1 : 0;

      if (payload?.product?.sellType === 'rent') {
        emi = 2;
      }
      return customFetch(
        `api/purchases/put`,
        {
          method: 'POST',
          body: JSON.stringify({
            buyerID: {
              userID: payload.buyerId,
            },
            sellerID: {
              userID: payload.product?.userId,
            },
            productID: payload.product?.productID,
            advertisementID: {
              advertisementID: payload.product?.advertisementId,
            },
            paid: payload.pay,
            is_emi: emi,
          }),
        },
        {ignoreRedirectOnFail: true},
      );
    },
  );
