import {ProductReal, PurchaseDto, StripePayment} from '@src/lib/api-schemas';

export const PURCHASE = 0;
export const PURCHASE_EMI = 1;
export const RENT = 2;

export const installments = (
  order: PurchaseDto,
  payments: StripePayment[],
): {
  left: number;
  paid: number;
} => {
  if (order?.is_emi === PURCHASE) {
    return {
      left: 0,
      paid: order?.paid,
    };
  }

  let paid = 0;

  for (const payment of payments) {
    paid += payment?.amount / 100;
  }

  if (order?.is_emi === PURCHASE_EMI) {
    return {
      left:
        Number((order?.advertisementID?.productID as ProductReal)?.price) -
        paid,
      paid,
    };
  }

  return {
    left: Infinity,
    paid,
  };
};
