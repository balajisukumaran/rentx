import {Table, Tag} from 'antd';
import {useWishlist} from './utils';
import wishlistStyle from './wishlist.module.less';
import {useNavigate} from 'react-router-dom';
import {useQuery} from 'react-query';
import {queries} from '@src/queries';
import {getImageSrc} from '@src/lib/utils';
import {ImageDto} from '@src/lib/api-schemas';

export const WishlistPage = () => {
  const [wishlistProducts, , removeWishlist] = useWishlist();
  const navigate = useNavigate();

  const {data: images} = useQuery(
    queries.product.images(wishlistProducts?.map((p) => p.productID) || []),
  );

  return (
    <div className={wishlistStyle.container}>
      <h4>Your wishlist</h4>

      <Table
        className="mt-5"
        pagination={false}
        columns={[
          {
            title: 'Image',
            render: (__, _, idx) => (
              <img
                src={getImageSrc(images?.[idx]?.[0] as ImageDto)}
                className="w-56 object-cover"
              />
            ),
          },
          {
            title: 'Name',
            dataIndex: 'name',
          },
          {
            title: 'Category',
            dataIndex: 'category',
            render: (record) => record.name,
          },
          {
            title: 'Location',
            dataIndex: 'area',
            render: (record) => record.city,
          },
          {
            title: 'Price',
            dataIndex: 'price',
            render: (price) => <>{price}$</>,
          },
          {
            title: 'Action',
            render: (record) => (
              <Tag
                color="red"
                onClick={(e) => {
                  e.preventDefault();
                  e.stopPropagation();
                  removeWishlist(record);
                }}
                className="cursor-pointer"
              >
                Remove
              </Tag>
            ),
          },
        ]}
        dataSource={wishlistProducts}
        onRow={(record) => {
          return {
            onClick: () => {
              navigate(`/product/${record.productID}`);
            },
          };
        }}
      />
    </div>
  );
};
