import {Avatar, Carousel, Spin} from 'antd';
import appStyles from './App.module.less';
import {Layout} from '@src/pages/dashboard/components/layout';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {Products} from '@src/pages/dashboard/components/products';
import {
  categories as dummyCategories,
  images,
  messaging,
  testimonials,
} from './temp';
import {useQuery} from 'react-query';
import {queries} from './queries';
import {useNavigate} from 'react-router-dom';
import {filterToRawSearch} from './pages/search/filter';

function App() {
  const navigate = useNavigate();
  const {data: products, isLoading} = useQuery({
    ...queries.product.list(),
    placeholderData: [],
  });

  const {data: categories} = useQuery(queries.product.categories);

  return (
    <div className={appStyles.container}>
      <Layout>
        <Carousel className={appStyles.carousel} dotPosition="bottom" autoplay>
          {images.map((img) => (
            <img className={appStyles['carousel-image']} src={img} key={img} />
          ))}
        </Carousel>

        <div className={appStyles.categories}>
          {categories?.slice(0, 4).map((category, idx) => (
            <div
              key={category.name}
              className={appStyles.category}
              onClick={() =>
                navigate(
                  `/search?${filterToRawSearch(new URLSearchParams(), {
                    categories: [category.name],
                  })}`,
                )
              }
            >
              <FontAwesomeIcon icon={dummyCategories[idx]?.icon} />
              <span className="text-xs">{category.name}</span>
            </div>
          ))}
        </div>

        <div className={appStyles['suggested-products']}>
          <h6>Suggested products </h6>

          {isLoading && <Spin />}
          {!isLoading && <Products products={(products || []).slice(0, 5)} />}
        </div>

        <div className={appStyles.messaging}>
          <h6 className="text-center">
            Renting solution that you always wanted
          </h6>

          <div className={appStyles['messaging-content']}>
            {messaging.map((message) => (
              <div key={message.title} className={appStyles.message}>
                <FontAwesomeIcon
                  icon={message.icon}
                  className="text-3xl mb-5"
                />
                <p>{message.title}</p>
                <p className="text-sm font-light text-gray-500 mt-2">
                  {message.description}
                </p>
              </div>
            ))}
          </div>
        </div>

        <div className={appStyles.testimonials}>
          <h6 className="text-center">Testimonials</h6>

          <div className={appStyles.reviews}>
            {testimonials.map((testimonial) => (
              <div className={appStyles.review}>
                <Avatar src={testimonial.avatar} size="large" />
                <p className="mt-5">{testimonial.user}</p>
                <p className="font-light text-sm mt-2">
                  {testimonial.testimonial}
                </p>
              </div>
            ))}
          </div>
        </div>
      </Layout>
    </div>
  );
}

export default App;
