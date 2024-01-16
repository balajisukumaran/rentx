export const calculateDeposit = (rent: number) => {
  return Math.floor(rent * 2 - rent * 0.1);
};
