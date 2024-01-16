import dayjs from 'dayjs';
import {faPrint} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {ProductReal, PurchaseDto, StripePayment} from '@src/lib/api-schemas';
import {useMemo} from 'react';
import {usePDF} from 'react-to-pdf';

type PrintPDFProps = {
  order: PurchaseDto;
  payments: StripePayment[];
};

export const PrintPDF = ({order, payments}: PrintPDFProps) => {
  const product = order?.advertisementID?.productID as ProductReal;
  const {toPDF, targetRef} = usePDF({
    filename: `${product?.name?.split(' ').join('-')}.pdf`,
  });

  const purchasedAt = useMemo(
    () =>
      dayjs(
        `${order?.date_of_sale?.[0]}/${order?.date_of_sale?.[1]}/${order?.date_of_sale?.[2]}`,
      ),
    [order],
  );

  return (
    <>
      <span
        className="text-xs text-gray-400 font-light mt-5 block cursor-pointer"
        style={{color: 'black'}}
        onClick={() => {
          console.log(targetRef.current);
          targetRef.current.style.display = 'block';
          toPDF();
          targetRef.current.style.display = 'none';
        }}
      >
        <FontAwesomeIcon icon={faPrint} className="mr-2" /> Print
      </span>

      <div
        ref={targetRef}
        style={{display: 'none', width: '100vw', padding: '100px'}}
        className="w-full"
      >
        <h1>Invoice</h1>
        <br />
        <h4>Product Details:</h4>
        <h6>
          <b>Name: </b> {product?.name}
        </h6>
        <h6>
          <b>Product Description: </b>
          {product?.description}
        </h6>
        <h6>
          <b>Product Price: </b>${product?.price}
        </h6>
        <br />
        <hr />
        <div style={{display: 'flex', justifyContent: 'space-between'}}>
          <div>
            <h4>Seller's Details</h4>
            <h6>
              <b>Email: </b> {order?.sellerID?.email}
            </h6>
            <h6>
              <b>First name: </b> {order?.sellerID?.firstName}
            </h6>
            <h6>
              <b>Last name: </b> {order?.sellerID?.lastName}
            </h6>
            <h6>
              <b>Phone Number: </b> {order?.sellerID?.phone}
            </h6>
          </div>
          <div>
            <h4>Buyer's Details</h4>
            <h6>
              <b>Email: </b> {order?.buyerID?.email}
            </h6>
            <h6>
              <b>First name: </b> {order?.buyerID?.firstName}
            </h6>
            <h6>
              <b>Last name: </b> {order?.buyerID?.lastName}
            </h6>
            <h6>
              <b>Phone number: </b> {order?.buyerID?.phone}
            </h6>
            <hr />
          </div>
        </div>

        <br />
        <br />
        <hr />
        <br />
        <hr />
        <h6>
          <b>Purchased at: </b>
          {purchasedAt.format()}
        </h6>

          {payments?.length && (
          <table>
            <tr>
              <th className="p-10 border-solid border-gray-400 border-2">
                Amount
              </th>
              <th className="p-10 border-solid border-gray-400 border-2">
                Paid at
              </th>
              <th className="p-10 border-solid border-gray-400 border-2">
                Address
              </th>
            </tr>
            {payments?.map((payment) => (
              <tr key={payment.id}>
                <td className="p-10 border-solid border-gray-400 border-2">
                  {payment.amount / 100}$
                </td>
                <td className="p-10 border-solid border-gray-400 border-2">
                  {dayjs(payment.created * 1000).format()}
                </td>
                <td className="p-10 border-solid border-gray-400 border-2">
                  {payment?.metadata?.address}
                </td>
              </tr>
            ))}
          </table>
        )}
      </div>
    </>
  );
};
