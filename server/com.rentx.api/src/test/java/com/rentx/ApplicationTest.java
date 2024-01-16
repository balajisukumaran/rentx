package com.rentx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentx.config.ApiResponse;
import com.rentx.dataaccess.repository.AreaRepository;
import com.rentx.dataaccess.repository.PurchaseRepository;
import com.rentx.dtos.*;
import com.rentx.entities.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.*;

/**
 * Integration tests
 */
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Integration test for register and login
     */
    @PostConstruct
    public void setUp() {
        try {
            SignUpDto signUpDto = new SignUpDto("balaji", "sukumaran", "123456789", "english"
                    , new Date()
                    , "test", "", 501, "balaji@gmail.com", "balaji", new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'});

            String signUpJson = objectMapper.writeValueAsString(signUpDto);

            mockMvc.perform(post("/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(signUpJson))
                    .andExpect(status().isCreated())
                    .andDo(print());
        } catch (Exception e) {
            System.out.println("Do nothing");
        }
    }

    /**
     * gets a jwt Token
     *
     * @return jwt token
     * @throws Exception might throw exception
     */
    public String getToken() throws Exception {
        CredentialsDto credentialsDto = new CredentialsDto("balaji", new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'});
        String credentialsDtoJson = objectMapper.writeValueAsString(credentialsDto);

        MvcResult login = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credentialsDtoJson))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        UserDto userDto = objectMapper.readValue(login.getResponse().getContentAsString(), UserDto.class);
        return userDto.getToken();
    }

    /**
     * Integration test for finding all areas
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testAreaFindAll() throws Exception {

        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/areas").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<AreaDto> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, entity.size());
    }

    /**
     * Integration test for advertisement find all
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testAdvertisementFindAll() throws Exception {

        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/advertisements").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<AdvertisementDto> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, entity.size());
    }


    /**
     * Integration test for categories find all
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testCategoriesFindAll() throws Exception {

        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/categories").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<CategoryDto> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(2, entity.size());
    }


    /**
     * Integration test for category by id
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testCategoriesGetCategoryById() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/category/id").header("Authorization", "Bearer " + token)
                        .param("categoryID", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<CategoryDto> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, entity.size());
    }


    /**
     * Integration test for message get
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testMessages() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(get("/messages").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<String> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(2, entity.size());
    }


    /**
     * Integration test for purchase history
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testPurchaseGetAllPurchasesDetails() throws Exception {
        String token = getToken();

        Purchase purchase = new Purchase();
        purchase.setPurchaseID(2);
        purchase.setDate_of_sale(LocalDateTime.now());
        purchase.setIs_emi(1);
        purchase.setPaid(20);
        purchase.setProductID(1);
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementID(1);
        purchase.setAdvertisementID(advertisement);
        purchase.setProductID(1);
        User user = new User();
        user.setUserID(3);
        purchase.setBuyerID(user);
        purchase.setSellerID(user);
        purchaseRepository.save(purchase);

        MvcResult result = mockMvc.perform(get("/api/purchases")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Purchase> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, entity.size());
    }

    /**
     * Integration test for place an order
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testPurchaseAddPurchaseDetails() throws Exception {
        String token = getToken();

        Purchase purchase = new Purchase();
        purchase.setIs_emi(1);
        purchase.setPaid(20);
        purchase.setProductID(1);
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementID(1);
        purchase.setAdvertisementID(advertisement);
        purchase.setProductID(1);
        User user = new User();
        user.setUserID(3);
        purchase.setBuyerID(user);
        purchase.setSellerID(user);

        String purchaseJson = objectMapper.writeValueAsString(purchase);

        MvcResult result = mockMvc.perform(post("/api/purchases/put")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseJson))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Object entity = objectMapper.readValue(jsonResponse, Object.class);
        assertNotNull(entity);


        MvcResult resultGet = mockMvc.perform(get("/api/purchases/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponseResultGet = resultGet.getResponse().getContentAsString();
        Object obj = objectMapper.readValue(jsonResponseResultGet, Object.class);
        assertNotNull(obj);
    }


    /**
     * Integration test for get review
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testReviewGetReviewForProduct() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/products/review/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Review> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(2, entity.size());
    }

    /**
     * Integration test for add review
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testReviewPostReview() throws Exception {
        String token = getToken();

        Review review = new Review();
        review.setDescription("asdas");
        ProductReal productReal = new ProductReal();
        productReal.setProductID(1);
        review.setProduct(productReal);
        review.setRating(5);

        String reviewJson = objectMapper.writeValueAsString(review);

        mockMvc.perform(post("/api/products/review/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isCreated())
                .andDo(print()).andReturn();
    }

    /**
     * Integration test for Health api
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testHealthHealthz() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(get("/healthz").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertEquals("Healthy", result.getResponse().getContentAsString());
    }


    /**
     * Integration test for complete wishlist apis
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testWishlist() throws Exception {
        String token = getToken();

        mockMvc.perform(post("/api/wishlist/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        MvcResult result = mockMvc.perform(get("/api/wishlist")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Wishlist> entity = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, entity.size());

        mockMvc.perform(delete("/api/wishlist/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
    }

    /**
     * Integration test for user profile update
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testUserProfileUpdateUserProfile() throws Exception {
        String token = getToken();

        User user = new User();
        user.setFirstName("test put");

        MvcResult result = mockMvc.perform(put("/api/profile/2")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk()).andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ApiResponse entity = objectMapper.readValue(jsonResponse, ApiResponse.class);
        assertNotNull(entity);


        MvcResult resultGet = mockMvc.perform(get("/api/profile/2").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponseResultGet = resultGet.getResponse().getContentAsString();
        User userResult = objectMapper.readValue(jsonResponseResultGet, User.class);
        assertNotNull(userResult);
    }


    /**
     * Integration test for get all user
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testUserFindAll() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/users").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<User> entity = objectMapper.readValue(jsonResponse, List.class);
        assertNotNull(entity);
        assertTrue(entity.size() > 0);
    }


    /**
     * Integration test for user verify
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testUserVerifyUser() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(put("/api/user/verify").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String output = result.getResponse().getContentAsString();
        assertEquals("1", output);
    }


    /**
     * Integration test for delete a user
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testUserDeleteUser() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(delete("/api/delete").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String output = result.getResponse().getContentAsString();
        assertEquals("true", output);
    }

    /**
     * Integration test for get all products
     *
     * @throws Exception might throw exception
     */
    @Test
    public void testUserGetAllProducts() throws Exception {
        String token = getToken();

        MvcResult result = mockMvc.perform(get("/api/user/product").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<ProductReal> entity = objectMapper.readValue(jsonResponse, List.class);
        assertNotNull(entity);
    }
}