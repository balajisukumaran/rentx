package com.rentx.controller;

import com.rentx.businessservices.ProductService;
import com.rentx.businessservices.interfaces.IUserService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dtos.UserDto;
import com.rentx.entities.ProductReal;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User controller
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userServices;
    private UserAuthenticationProvider userAuthenticationProvider;
    private ProductService productService;

    /**
     * Controller for user-repo
     * @param userServices user business service object
     */
    public UserController(IUserService userServices, UserAuthenticationProvider userAuthenticationProvider, ProductService productService) {
        this.userServices = userServices;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.productService = productService;
    }

    /**
     * return all users
     * @return all users
     */
    @GetMapping("/users")
    public List<UserDto> findAll(){
        return userServices.findAll();
    }

    /**
     * Change status of user to verify
     * @param authorizationHeader
     * @return
     */
    @PutMapping("/user/verify")
    public int verifyUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        userServices.verifyUser("verified", userDto.getUserID());
        return 1;
    }

    /**
     * delete mapping for delete user
     * @param authorizationHeader authorizationHeader
     * @return delete user
     */
    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        userServices.deleteUser(userDto.getUserID());
        return true;
    }

    /**
     * Returns products created by the user
     * @param authorizationHeader is a header to extract token
     * @return List of products
     */
    @GetMapping("/user/product")
    public List<ProductReal> getAllProducts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        return productService.getProducts(userDto.getUserID());
    }

    /**
     * post mapping for add product
     * @param authorizationHeader authorizationHeader
     * @param productReal productReal
     * @return updated product service
     */
    @PostMapping("/user/product")
    public ProductReal addProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody ProductReal productReal){
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        return productService.addProduct(userDto.getUserID(), productReal);
    }

    /**
     * put mapping for update product
     * @param authorizationHeader authorizationHeader
     * @param productReal productReal
     * @return update product iini product service
     */
    @PutMapping("/user/product")
    public ProductReal updateProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody ProductReal productReal){
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        return productService.updateProduct(userDto.getUserID(), productReal);
    }

    /**
     * delete mapping for delete product
     * @param authorizationHeader authorizationHeader
     * @param productReal productReal
     * @return delete product
     */
    @DeleteMapping("/user/product")
    public int deleteProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody ProductReal productReal){
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        productService.deleteProduct(userDto.getUserID(), productReal);
        return 1;
    }
}