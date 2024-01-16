import {createQueryKeys} from '@lukemorales/query-key-factory';
import {StripePayment} from '@src/lib/api-schemas';
import {STRIPE_SECRET_KEY} from '@src/lib/constants';

export const authHeader = {
  Authorization: `Bearer ${STRIPE_SECRET_KEY}`,
};

export const payments = createQueryKeys('payments', {
  customer: (email: string) => ({
    queryKey: [email],
    queryFn: (): Promise<{id: string}> =>
      fetch(
        `https://api.stripe.com/v1/customers?email=${encodeURIComponent(
          email,
        )}`,
        {
          headers: {
            ...authHeader,
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        },
      )
        .then((res) => res.json())
        .then((res) => res.data?.[0]),
  }),
  intents: (customerId: string) => ({
    queryKey: [customerId],
    queryFn: (): Promise<Array<StripePayment>> =>
      fetch(
        `https://api.stripe.com/v1/payment_intents/search?query=customer:'${customerId}'`,
        {
          headers: {
            ...authHeader,
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        },
      )
        .then((res) => res.json())
        .then((res) => res?.data || []),
  }),
});
