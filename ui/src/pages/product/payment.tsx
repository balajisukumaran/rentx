import {useStripe, useElements, CardElement} from '@stripe/react-stripe-js';
import {STRIPE_SECRET_KEY} from '@src/lib/constants';
import {Button} from 'antd';

export const Payment = () => {
  const stripe = useStripe();
  const elements = useElements();

  return (
    <>
      <CardElement />
      <Button
        onClick={async () => {
          if (!stripe || !elements) {
            return;
          }

          const cardElement = elements.getElement(CardElement);

          if (!cardElement) {
            return;
          }

          await stripe.paymentRequest({
            currency: 'cad',
            country: 'CA',
            total: {amount: 1000, label: 'price'},
          });

          const result = await stripe.confirmCardPayment(STRIPE_SECRET_KEY, {
            payment_method: {
              card: cardElement,
            },
          });

          console.log(result);
        }}
      >
        Pay
      </Button>
    </>
  );
};
