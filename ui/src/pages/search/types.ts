export enum SORT_BY {
  PRICE_HIGH_TO_LOW,
  PRICE_LOW_TO_HIGH,
  NONE,
}

export type SearchFilter = {
  nameOrDescription?: string;
  rentRange?: [number, number];
  categories?: string[];
  page?: number;
  limit?: number;
  location?: string[];
  sortBy?: SORT_BY;
};
