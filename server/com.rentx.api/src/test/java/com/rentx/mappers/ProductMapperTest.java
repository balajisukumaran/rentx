package com.rentx.mappers;

import com.rentx.dataaccess.ProductDAO;
import com.rentx.dtos.ProductDto;
import com.rentx.entities.Product;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    /**
     * product object
     */
    Product product = new Product();

    /**
     * test method for product mapper
     */
    public ProductMapperTest(){

        //user setup
        product = new Product();
        product.setFirst_name("sa");
        product.setUserID("123");
        product.setEmail("test@gmail.com");
        product.setAreaID(123);
        product.setJoining_date(new Date(2020,2,2));
        product.setPref_language("t");
        product.setPrivacy("privacy");
        product.setStatus("test");
        product.setPhone("12344232");
        product.setAdvertisement_id(23);
        product.setAdvt_title("adsa");
        product.setPost_date(new Date(2020,2,2));
        product.setExpiry_date(new Date(2020,2,2));
        product.setLast_name("asdas");
        product.setProductID(1231);
        product.setProduct_name("asda");
        product.setProduct_description("asdas");
        product.setPrice(1231);
        product.setCategory_id(12);
        product.setCategory_name("asd");
        product.setAreaID(12);
        product.setArea_name("asda");
        product.setCity("asda");
        product.setState("asdasd");
        product.setCountry("ewr2332");

    }

    /**
     * test case for the product DTO
     */
    @Test
    void toProductDto() {
        ProductDto productDto = ProductMapper.toProductDto(product);
        assertEquals(productDto.getFirstName(),product.getFirst_name());
        assertEquals(productDto.getUserId(),product.getUserID());
        assertEquals(productDto.getEmail(),product.getEmail());
        assertEquals(productDto.getAreaId(),product.getAreaID());
        assertEquals(productDto.getJoiningDate(),product.getJoining_date());
        assertEquals(productDto.getPrefLanguage(),product.getPref_language());
        assertEquals(productDto.getPrivacy(),product.getPrivacy());
        assertEquals(productDto.getStatus(),product.getStatus());
        assertEquals(productDto.getPhone(),product.getPhone());
        assertEquals(productDto.getAdvertisementId(),product.getAdvertisement_id());
        assertEquals(productDto.getAdvtTitle(),product.getAdvt_title());
        assertEquals(productDto.getPostDate(),product.getPost_date());
        assertEquals(productDto.getExpiryDate(),product.getExpiry_date());
        assertEquals(productDto.getLastName(),product.getLast_name());
        assertEquals(productDto.getProductID(),product.getProductID());
        assertEquals(productDto.getProductName(),product.getProduct_name());
        assertEquals(productDto.getProductDescription(),product.getProduct_description());
        assertEquals(productDto.getPrice(),product.getPrice());
        assertEquals(productDto.getCategoryId(),product.getCategory_id());
        assertEquals(productDto.getCategoryName(),product.getCategory_name());
        assertEquals(productDto.getAreaId(),product.getAreaID());
        assertEquals(productDto.getAreaName(),product.getArea_name());
        assertEquals(productDto.getCity(),product.getCity());
        assertEquals(productDto.getState(),product.getState());
        assertEquals(productDto.getCountry(),product.getCountry());
    }


    /**
     * test case to check product's null DTO
     */
    @Test
    void toProductDtoNull() {
        ProductDto productDto = ProductMapper.toProductDto(null);
        assertNull(productDto);
    }


    /**
     * test case to map null DTO
     */
    @Test
    void toProductDtosNull() {

        List<ProductDto> productDto = ProductMapper.toProductDtos(null);
        assertNull(productDto);
    }


    /**
     * test case for the product DTO
     */
    @Test
    void toProductDtos() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        List<ProductDto> productDto = ProductMapper.toProductDtos(products);
        assertEquals(productDto.size(),products.size());
    }
}