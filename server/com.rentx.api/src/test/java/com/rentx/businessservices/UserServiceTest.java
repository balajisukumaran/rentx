package com.rentx.businessservices;

import com.rentx.config.UserAuthenticationProvider;
import com.rentx.controller.UserController;
import com.rentx.dataaccess.interfaces.IUserDAO;
import com.rentx.dataaccess.repository.*;
import com.rentx.dtos.CredentialsDto;
import com.rentx.dtos.ResetPassword;
import com.rentx.dtos.SignUpDto;
import com.rentx.dtos.UserDto;
import com.rentx.entities.*;
import com.rentx.entities.enums.IdentifyBy;
import com.rentx.utils.UserServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    /**
     * User Service
     */
    UserService userService;
    /**
     * IUser DAO
     */
    IUserDAO userDAO;
    /**
     * users list object
     */
    List<User> users;
    /**
     * password encoder class
     */
    PasswordEncoder passwordEncoder;
    /**
     * credentials DTO
     */
    CredentialsDto credentialsDto;
    /**
     * sign up DTO
     */
    SignUpDto signUpDto;
    /**
     * reset password class
     */
    ResetPassword resetPassword;
    /**
     * user repository
     */
    UserRepository userRepository;
    /**
     * advertisement repository
     */
    AdvertisementRepository advertisementRepository;
    /**
     * product real repository
     */
    ProductRealRepository productRealRepository;
    /**
     * Electronic Gadget Repository
     */
    ElectronicGadgetRepository electronicGadgetRepository;
    /**
     * books repository
     */
    BooksRepository booksRepository;
    /**
     * Kitchen Appliance Repository
     */
    KitchenApplianceRepository kitchenApplianceRepository;
    /**
     * Furniture Repository
     */
    FurnitureRepository furnitureRepository;
    /**
     * product real
     */
    ProductReal productReal;
    /**
     * category object
     */
    Category category;
    /**
     * user service utils
     */
    UserServiceUtils userServiceUtils;
    /**
     * Electronic Gadget
     */
    ElectronicGadget electronicGadget;
    /**
     * Kitchen Appliance
     */
    KitchenAppliance kitchenAppliance;
    /**
     * furniture
     */
    Furniture furniture;
    /**
     * books objects
     */
    Books books;
    /**
     * Advertisement Service
     */
    AdvertisementService advertisementService;
    /**
     * Product Service
     */
    ProductService productService;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void setUp(){
        productReal = mock(ProductReal.class);
        category = mock(Category.class);
        userServiceUtils = mock(UserServiceUtils.class);
        electronicGadget = mock(ElectronicGadget.class);
        furniture = mock(Furniture.class);
        kitchenAppliance = mock(KitchenAppliance.class);
        books = mock(Books.class);
    }

    /**
     * test case for user service
     */
    public UserServiceTest() {
        userDAO= mock(IUserDAO.class);
        this.userRepository = mock(UserRepository.class);
        this.advertisementRepository = mock(AdvertisementRepository.class);
        this.productRealRepository = mock(ProductRealRepository.class);
        this.electronicGadgetRepository = mock(ElectronicGadgetRepository.class);
        this.booksRepository = mock(BooksRepository.class);
        this.kitchenApplianceRepository = mock(KitchenApplianceRepository.class);
        this.furnitureRepository = mock(FurnitureRepository.class);
        advertisementService = mock(AdvertisementService.class);
        this.productService = Mockito.mock(ProductService.class);
        this.userService=new UserService(userDAO, userRepository);
        passwordEncoder= mock(PasswordEncoder.class);
        credentialsDto = new CredentialsDto("123","dasd".toCharArray());
        signUpDto = new SignUpDto("f","l","1","s",new Date(),"s","s",1,"s","a","a".toCharArray());
        resetPassword = new ResetPassword(123,"s","l","123123","asd",new Date(),"s","s",1,"asda","sadas","das".toCharArray(),"sada");
   }

    /**
     * test case to find all new users in DAO
     */
    @Test
    void findAll() {
        users=new ArrayList<>();
        users.add(new User());
        users.add(new User());
        Mockito.when(userDAO.findAll()).thenReturn(users);
        var genUser=userService.findAll();
        assertEquals(genUser.size(),users.size());
    }

    /**
     * test case to insert user dat ain service and assert
     */
    @Test
    void insert() {
        Mockito.when(userDAO.insert(any())).thenReturn(new User());
        var genUser=userService.insert(new UserDto());
        assertNotNull(genUser);
    }

    /**
     * test case for login function for user with test data and user
     */
    @Test
    void login() {
        User u= new User();
        u.setLogin("123");
        u.setPassword("dasd");
        Mockito.when(userDAO.findBy(any(String.class),any(IdentifyBy.class))).thenReturn(Optional.of(u));
        Mockito.when(passwordEncoder.matches(any(CharSequence.class),any(String.class))).thenReturn(true);
       try {
           var genUserDto = userService.login(credentialsDto);
       }
       catch (Exception e){
           assertEquals(e.getMessage(),"User is deleted !!");
       }
    }

    /**
     * test case for user already exists with exception
     */
    @Test
    void registerAlreadyExists() {
        User u= new User();
        u.setLogin("123");
        u.setPassword("dasd");
        Mockito.when(userDAO.findBy(any(String.class),any(IdentifyBy.class))).thenReturn(Optional.of(u));
        Mockito.when(userDAO.insert(any(User.class))).thenReturn(u);
        try {
            var regUser = userService.register(signUpDto);
        }
        catch (Exception e){
            assertEquals(e.getMessage(),"Login already exists");
        }
    }

    /**
     * test case to register the new user with test data
     */
    @Test
    void register() {
        User u= new User();
        u.setLogin("123");
        u.setPassword("dasd");
        Mockito.when(userDAO.findBy(any(String.class),any(IdentifyBy.class))).thenReturn(null);
        Mockito.when(userDAO.insert(any(User.class))).thenReturn(u);
        var regUser= userService.register(signUpDto);
        assertNotNull(regUser);
    }

    /**
     * test case to re register user
     */
    @Test
    void reRegister() {
        User u= new User();
        u.setLogin("das");
        u.setPassword("sadas");
        Mockito.when(userDAO.insert(any(User.class))).thenReturn(u);
        var reRegUser= userService.reRegister(resetPassword);
        assertNotNull(reRegUser);
    }

    /**
     * test case to find user by login details
     */
    @Test
    void findByLogin() {
        User u= new User();
        u.setLogin("123");
        u.setPassword("dasd");
        Mockito.when(userDAO.findBy(any(String.class),any(IdentifyBy.class))).thenReturn(Optional.of(u));
        var genUser=userService.findByLogin("123");
        assertNotNull(genUser);
    }

    /**
     * test case to find user by email
     */
    @Test
    void findByEmail() {
        User u= new User();
        u.setLogin("123");
        u.setPassword("dasd");
        Mockito.when(userDAO.findBy(any(String.class),any(IdentifyBy.class))).thenReturn(Optional.of(u));
        var genUser=userService.findByEmail("123");
        assertNotNull(genUser);
    }

    /**
     * test case to find user by reset token
     */
    @Test
    void findByResetToken() {
        User u= new User();
        u.setLogin("123");
        u.setPassword("dasd");
        Mockito.when(userDAO.findBy(any(String.class),any(IdentifyBy.class))).thenReturn(Optional.of(u));
        var genUser=userService.findByResetToken("123");
        assertNotNull(genUser);
    }

    /*
     * Test case to verify the user
     */
    @Test
    public void testVerifyUser() {
        // Arrange: Mock dependencies
        UserDto userDto = new UserDto(); // Create a mock UserDto object
        userDto.setUserID(123); // Set user ID
        UserAuthenticationProvider userAuthenticationProvider = mock(UserAuthenticationProvider.class);
        when(userAuthenticationProvider.getUserByToken(anyString())).thenReturn(userDto);

        UserService userServices = mock(UserService.class);
        ProductService productService = mock(ProductService.class);
        UserController controller = new UserController(userServices, userAuthenticationProvider, productService);
        //Act:  Call the method with a mock Authorization header
        String authorizationHeader = HttpHeaders.AUTHORIZATION + ": Bearer your_mock_token";
        
        int result = controller.verifyUser(authorizationHeader);
        // Assert: Verify that the method behaves as expected
        assertEquals(1, result);
        verify(userServices, times(1)).verifyUser("verified", 123);
    }

    /**
     * Tetsing: verify valid user
     */
    @Test
    public void testVerifyUserWithValidUser() {
        // Arrange: Mocking behavior of userRepository
        User mockUser = mock(User.class);
        when(userRepository.findByUserID(anyInt())).thenReturn(mockUser);
        when(mockUser.getStatus()).thenReturn("previousStatus");

        // Act: Call the method to test
        userService.verifyUser("newStatus", 123);

        // Assert: Verify that findByUserID and save methods were called
        verify(userRepository, times(1)).findByUserID(eq(123));
        verify(mockUser, times(1)).setStatus(eq("newStatus"));
        verify(userRepository, times(1)).save(eq(mockUser));
    }

    /**
     * test case to verify user with invalid user
     */
    @Test
    public void testVerifyUserWithInValidUser() {
        // Arrange: Mocking behavior of userRepository
        String status = "verified";
        int userId = 101;
        when(userRepository.findByUserID(userId)).thenThrow(new RuntimeException("Simulating an exception on Verify Test case"));

        // Act: Call the method to test
        userService.verifyUser("newStatus", userId);

        // Assert: Verify that findByUserID and save methods were called
        verify(userRepository, times(1)).findByUserID(eq(userId));
    }

    /**
     * test case to delete user
     */
    @Test
    public void testDeleteUser() {
        int userID = 123;
        User user = new User();
        user.setUserID(userID);
        user.setIsExists(1);
        Mockito.when(userRepository.findByUserID(userID)).thenReturn(user);
        userService.deleteUser(userID);
        assertEquals(0, user.getIsExists());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    /**
     * test case to check delete user with invalid user exception
     */
    @Test
    public void testDeleteUserWithInValidUser() {
        // Arrange: Mocking behavior of userRepository
        int userId = 101;
        when(userRepository.findByUserID(userId)).thenThrow(new RuntimeException("Simulating an exception on Delete  Test case"));

        // Act: Call the method to test
        userService.deleteUser(userId);

        // Assert: Verify that findByUserID and save methods were called
        verify(userRepository, times(1)).findByUserID(userId);
    }

    /**
     * test case to verify user
     */
    @Test
    public void verifyUserTest() {
        int userID = 123;
        String status = "verified";
        User user = new User();
        user.setUserID(userID);
        Mockito.when(userRepository.findByUserID(userID)).thenReturn(user);
        userService.verifyUser(status, userID);
        assertEquals(status, user.getStatus());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    /**
     * test case for user not found
     */
    @Test
    public void userNotFoundVerifyUser() {
        int userID = 123;
        String status = "verified";
        Mockito.when(userRepository.findByUserID(userID)).thenReturn(null);
        userService.verifyUser(status, userID);
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

}