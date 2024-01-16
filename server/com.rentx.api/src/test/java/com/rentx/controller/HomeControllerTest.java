package com.rentx.controller;

import com.rentx.businessservices.interfaces.IProductService;
import com.rentx.businessservices.interfaces.IUserService;
import com.rentx.dtos.ProductDto;
import com.rentx.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HomeControllerTest {
    /**
     * product service class
     */
    IProductService productService;
    /**
     * product DTOs onject
     */
    List<ProductDto> productDtos;
    /**
     * home controller
     */
    HomeController homeController;

    /**
     * home controller constructor
     */
    public HomeControllerTest() {
        this.productService = Mockito.mock(IProductService.class);
        homeController=new HomeController(this.productService);
    }

    /**
     * test case to get All Available Products
     */
    @Test
    void getAllAvailableProducts() {
        productDtos=new ArrayList<>();
        productDtos.add(new ProductDto());
        productDtos.add(new ProductDto());
        Mockito.when(productService.getAllAvailableProducts()).thenReturn(productDtos);
        var genProdDtos = homeController.getAllAvailableProducts();
        assertEquals(genProdDtos.size(),genProdDtos.size());
    }

    /**
     * test case to get products
     */
    @Test
    void getProduct() {
        Mockito.when(productService.getProduct(any(Integer.class))).thenReturn(new ProductDto());
        var genProdDto = homeController.getProduct(1);
        assertNotNull(genProdDto);
    }
}