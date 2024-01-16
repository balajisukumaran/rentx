package com.rentx.mappers;

import com.rentx.dtos.ProductDto;
import com.rentx.entities.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Product mapper class
 */
public class ProductMapper {

    /**
     * converts product object to product dto
     * @param product product object
     * @return product dto
     */
    public static ProductDto toProductDto(Product product){

        ProductDto productDto = null;

        if(product != null) {

            productDto = new ProductDto();
            productDto.setAdvertisementId(product.getAdvertisement_id());
            productDto.setAdvtTitle(product.getAdvt_title());
            productDto.setPostDate(product.getPost_date());
            productDto.setExpiryDate(product.getExpiry_date());
            productDto.setUserId(product.getUserID());
            productDto.setPhone(product.getPhone());
            productDto.setFirstName(product.getFirst_name());
            productDto.setLastName(product.getLast_name());
            productDto.setPrefLanguage(product.getPref_language());
            productDto.setJoiningDate(product.getJoining_date());
            productDto.setPrivacy(product.getPrivacy());
            productDto.setStatus(product.getStatus());
            productDto.setEmail(product.getEmail());
            productDto.setProductID(product.getProductID());
            productDto.setProductName(product.getProduct_name());
            productDto.setProductDescription(product.getProduct_description());
            productDto.setPrice(product.getPrice());
            productDto.setCategoryId(product.getCategory_id());
            productDto.setCategoryName(product.getCategory_name());
            productDto.setAreaId(product.getAreaID());
            productDto.setAreaName(product.getArea_name());
            productDto.setCity(product.getCity());
            productDto.setState(product.getState());
            productDto.setCountry(product.getCountry());
            productDto.setSellType(product.getSellType());
        }

        return productDto;
    }

    /**
     * converts product objects to product dtos
     * @param allAvailableProducts product objects
     * @return product dtos
     */
    public static List<ProductDto> toProductDtos(List<Product> allAvailableProducts) {

        List<ProductDto> productDtos = null;
        if(allAvailableProducts != null){
            productDtos = new ArrayList<>();

            for(Product product: allAvailableProducts)
                productDtos.add(toProductDto(product));
        }

        return productDtos;
    }
}
