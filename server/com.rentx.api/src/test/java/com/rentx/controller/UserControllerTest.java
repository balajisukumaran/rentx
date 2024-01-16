package com.rentx.controller;

import com.rentx.businessservices.ProductService;
import com.rentx.businessservices.interfaces.IProductService;
import com.rentx.businessservices.interfaces.IUserService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dtos.UserDto;
import com.rentx.entities.Category;
import com.rentx.entities.ProductReal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    /**
     * instance of userService
     */
    IUserService userService;
    /**
     * instance of product service
     */
    ProductService productService;
    /**
     * object for User Authentication Provider
     */
    UserAuthenticationProvider userAuthenticationProvider;
    /**
     * list of user DTO
     */
    List<UserDto> userDtos;
    /**
     * private mock Mvc
     */
    private MockMvc mockMvc;
    /**
     * user controller
     */
    UserController userController;

    /**
     * user controller constructor
     */
    public UserControllerTest() {
        this.userService = Mockito.mock(IUserService.class);
        this.productService = Mockito.mock(ProductService.class);
        this.userAuthenticationProvider = Mockito.mock(UserAuthenticationProvider.class);
        userController=new UserController(this.userService, this.userAuthenticationProvider, this.productService);
    }

    /**
     * test case to find all in user dto
     */
    @Test
    void findAll() {
        userDtos=new ArrayList<>();
        userDtos.add(new UserDto());
        userDtos.add(new UserDto());
        when(userService.findAll()).thenReturn(userDtos);
        var genUserDtos = userController.findAll();

        assertEquals(genUserDtos.size(),userDtos.size());
    }

    /**
     * Test case to verify user
     */
    @Test
    public void testVerifyUser() {
        // Arrange
        String validToken = "validToken";
        String authorizationHeader = "Bearer " + validToken;
        UserDto userDto = new UserDto();
        userDto.setUserID(123);

        when(userAuthenticationProvider.getUserByToken(validToken)).thenReturn(userDto);

        // Act
        int result = userController.verifyUser(authorizationHeader);

        // Assert
        verify(userAuthenticationProvider, times(1)).getUserByToken(validToken);
        verify(userService, times(1)).verifyUser("verified", 123);
        assertEquals(1, result);
    }

    /**
     * Testing getAllProducts API
     */
    @Test
    void getAllProducts() {
        // Arrange
        String validAuthorizationHeader = "Bearer validToken";
        UserDto mockUserDto = new UserDto();
        mockUserDto.setUserID(1);
        when(this.userAuthenticationProvider.getUserByToken("validToken")).thenReturn(mockUserDto);
        List<ProductReal> mockProducts = Arrays.asList(new ProductReal(), new ProductReal());
        when(productService.getProducts(mockUserDto.getUserID())).thenReturn(mockProducts);

        // Act: Calling controller method
        List<ProductReal> result = userController.getAllProducts(validAuthorizationHeader);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    /**
     * Testing addProduct api
     */
    @Test
    public void testAddProduct() {
        // Arrange
        String validAuthorizationHeader = "Bearer validToken";
        ProductReal productReal = new ProductReal();
        productReal.setProductID(1);
        UserDto mockUserDto = new UserDto();
        mockUserDto.setUserID(1);
        when(userAuthenticationProvider.getUserByToken(anyString())).thenReturn(mockUserDto);
        when(productService.addProduct(anyInt(), any(ProductReal.class))).thenReturn(productReal);
        
        // Act
        ProductReal responseEntity = userController.addProduct(validAuthorizationHeader, productReal);

        // Assert
        assertEquals(productReal, responseEntity);
    }

    /**
     * Testing update product api
     */
    @Test
    public void testUpdateProduct() {
        // Arrange
        String validAuthorizationHeader = "Bearer validToken";
        ProductReal productReal = new ProductReal();
        productReal.setProductID(1);
        UserDto mockUserDto = new UserDto();
        mockUserDto.setUserID(1);
        when(userAuthenticationProvider.getUserByToken(anyString())).thenReturn(mockUserDto);
        when(productService.updateProduct(anyInt(), any(ProductReal.class))).thenReturn(productReal);

        // Act
        ProductReal responseEntity = userController.updateProduct(validAuthorizationHeader, productReal);

        // Assert
        assertEquals(productReal, responseEntity);
    }

    @Test
    void testDeleteUser(){
        String authorizationHeader = "Bearer validToken";
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        Mockito.when(userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1])).thenReturn(userDto);
        Mockito.doNothing().when(userService).deleteUser(userDto.getUserID());
        boolean result = userController.deleteUser(authorizationHeader);
        assertTrue(result);
    }

    /**
     * Testing delete product api
     */
    @Test
    public void testDeleteProduct() {
        // Arrange
        String validAuthorizationHeader = "Bearer validToken";
        ProductReal productReal = new ProductReal();
        productReal.setProductID(1);
        productReal.setName("Test Product");
        productReal.setDescription("Test Description");
        productReal.setPrice(50);
        Category category = mock(Category.class);
        when(category.getCategoryID()).thenReturn(11);
        productReal.setCategory(category);
        UserDto mockUserDto = new UserDto();
        mockUserDto.setUserID(123);
        when(userAuthenticationProvider.getUserByToken(anyString())).thenReturn(mockUserDto);
        when(productService.deleteProduct(mockUserDto.getUserID(), productReal)).thenReturn(1);

        // Act
        userController.deleteProduct(validAuthorizationHeader, productReal);

        // Assert: Verify that the expected methods were called with the correct arguments
        verify(productService, times(1)).deleteProduct(123, productReal);
    }
}