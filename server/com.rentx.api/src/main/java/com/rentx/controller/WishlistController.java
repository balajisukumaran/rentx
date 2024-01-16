package com.rentx.controller;

import com.rentx.businessservices.ProductService;
import com.rentx.businessservices.UserService;
import com.rentx.businessservices.WishlistService;
import com.rentx.businessservices.interfaces.IUserService;
import com.rentx.businessservices.interfaces.IWishlistService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dataaccess.repository.ProductRealRepository;
import com.rentx.dataaccess.repository.UserRepository;
import com.rentx.dtos.UserDto;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private IWishlistService wishlistService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRealRepository productRealRepository;

    private final UserAuthenticationProvider userAuthenticationProvider;

    /**
     * constructor for wishlist controller
     * @param wishlistService wishlistService
     * @param userAuthenticationProvider userAuthenticationProvider
     * @param userRepository userRepository
     * @param productRealRepository productRealRepository
     */
    public WishlistController(IWishlistService wishlistService, UserAuthenticationProvider userAuthenticationProvider, UserRepository userRepository, ProductRealRepository productRealRepository) {
        this.wishlistService = wishlistService;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.userRepository = userRepository;
        this.productRealRepository = productRealRepository;
    }

    /**
     * get mapping to get Wishlist For User
     * @param authorizationHeader authorizationHeader
     * @return new response entity of wishlist
     */
    @GetMapping
    public ResponseEntity<List<ProductReal>> getWishlistForUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        User user = userRepository.findByUserID(userDto.getUserID());
        List<ProductReal> wishlist = wishlistService.getWishlistForUser(user);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    /**
     * post mapping for add to wishlist
     * @param authorizationHeader authorizationHeader
     * @param product_id product_id
     * @return new response entity got wish list
     */
    @PostMapping("/{product_id}")
    public ResponseEntity<String> addToWishlist(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @PathVariable int product_id) {
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        User user = userRepository.findByUserID(userDto.getUserID());
        ProductReal product = productRealRepository.findById(product_id).orElse(null);
        if (product != null) {
            wishlistService.addToWishlist(user, product);
            return new ResponseEntity<>("Product added to the wishlist.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * delete mapping for the remove from wishlist
     * @param authorizationHeader authorizationHeader
     * @param product_id product_id
     * @return wihslist removed list in response
     */
    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> removeFromWishlist(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @PathVariable int product_id) {
        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        User user = userRepository.findByUserID(userDto.getUserID());
        ProductReal product = productRealRepository.findById(product_id).orElse(null);
        wishlistService.removeFromWishlist(user, product);
        return new ResponseEntity<>("Product removed from the wishlist.", HttpStatus.OK);
    }

}
