package com.rentx.controller;

import com.rentx.businessservices.interfaces.IProductService;
import com.rentx.dtos.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Home controller
 */
@RestController
@RequestMapping("/api")
public class HomeController {

    private final IProductService productService;

    /**
     * Constructor for home page controller
     * @param productService product business service object
     */
    public HomeController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * gets all the available products
     * @return product dtos
     */
    @GetMapping("/home/products")
    public List<ProductDto> getAllAvailableProducts(){
        return productService.getAllAvailableProducts();
    }

    /**
     * get a particular product by product id
     * @param productId product id
     * @return product dto
     */
    @GetMapping("/home/products/{productId}")
    public ProductDto getProduct(@PathVariable int productId){
        return productService.getProduct(productId);
    }
}
