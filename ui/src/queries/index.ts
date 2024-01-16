import {mergeQueryKeys} from '@lukemorales/query-key-factory';
import {user} from './user';
import {external} from './external';
import {product} from './product';
import {payments} from './payment';

export const queries = mergeQueryKeys(user, external, product, payments);
