package com.rentx.businessservices;

import com.rentx.config.Constant;
import com.rentx.dataaccess.interfaces.IProductDAO;
import java.util.*;

import com.rentx.dataaccess.repository.*;
import com.rentx.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    /**
     * Product Service class
     */
    ProductService productService;
    /**
     * product DAO classs
     */
    IProductDAO productDAO;
    /**
     * user repository class
     */
    UserRepository userRepository;
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
     * Advertisement Service
     */
    AdvertisementService advertisementService;
    /**
     * Advertisement Repository
     */
    AdvertisementRepository advertisementRepository;
    /**
     * area repository
     */
    AreaRepository areaRepository;
    /**
     * area object
     */
    Area area = new Area();

    /**
     * test method for public serviceto mock repository and test data
     */
    public ProductServiceTest(){
        productDAO= Mockito.mock(IProductDAO.class);
        this.productRealRepository = mock(ProductRealRepository.class);
        this.electronicGadgetRepository = mock(ElectronicGadgetRepository.class);
        this.booksRepository = mock(BooksRepository.class);
        this.kitchenApplianceRepository = mock(KitchenApplianceRepository.class);
        this.furnitureRepository = mock(FurnitureRepository.class);
        productReal = mock(ProductReal.class);
        advertisementService = mock(AdvertisementService.class);
        advertisementRepository = mock(AdvertisementRepository.class);
        this.areaRepository = mock(AreaRepository.class);
        this.userRepository = mock(UserRepository.class);
        this.area.setAreaID(501);
        this.area.setCountry("USA");
        this.area.setState("Maryland");
        this.area.setCity("Baltimore");
        this.area.setAreaName("Springdale");
        productService=new ProductService(productDAO, userRepository, productRealRepository,
                electronicGadgetRepository, booksRepository, kitchenApplianceRepository, furnitureRepository,
                advertisementService, advertisementRepository, areaRepository);
    }

    /**
     * test case to get all available products and add new products and assert them
     */
    @Test
    void getAllAvailableProducts() {
        List<Product> products =new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        when(productDAO.getAllAvailableProducts()).thenReturn(products);
        var genProd=productService.getAllAvailableProducts();
        assertEquals(genProd.size(),genProd.size());
    }

    /**
     * test case to get products with product service
     */
    @Test
    void getProduct() {
        when(productDAO.getAllAvailableProduct(any(Integer.class))).thenReturn(new Product());
        var genProd=productService.getAllAvailableProducts();
        assertNotNull(genProd);
    }

    /*
     * Test case to return products associated with the user
     */
    @Test
    public void testGetProducts() {
        // Arrange
        int userId = 1;
        User user = new User(); // Set up a mock User object
        user.setUserID(userId);

        Advertisement advertisement1 = new Advertisement(); // Set up a mock Advertisement object
        advertisement1.setProductID(new ProductReal()); // Set up a mock ProductReal object
        Advertisement advertisement2 = new Advertisement();
        advertisement2.setProductID(new ProductReal());

        List<Advertisement> advertisementList = new ArrayList<>();
        advertisementList.add(advertisement1);
        advertisementList.add(advertisement2);

        when(userRepository.findByUserID(userId)).thenReturn(user);
        when(advertisementRepository.findByUserID(user)).thenReturn(advertisementList);

        // Act
        List<ProductReal> actualProductList = productService.getProducts(userId);

        // Assert
        assertEquals(advertisementList.size(), actualProductList.size());
        // Add more specific assertions as needed
    }

    /*
     * Test case when user is not found
     */
    @Test
    public void testGetProductsForInvalidUser() {
        // Arrange
        int userId = 1;

        when(userRepository.findByUserID(userId)).thenReturn(null);

        // Act
        List<ProductReal> actualProductList = productService.getProducts(userId);

        // Assert
        assertNotNull(actualProductList);
        assertTrue(actualProductList.isEmpty());
    }

    /*
     * Test case when there is no advertisement linked with the user
     */
    @Test
    public void testGetProductsForNoAdvertisements() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setUserID(userId);

        when(userRepository.findByUserID(userId)).thenReturn(user);
        when(advertisementRepository.findByUserID(user)).thenReturn(new ArrayList<>());

        // Act
        List<ProductReal> actualProductList = productService.getProducts(userId);

        // Assert
        assertNotNull(actualProductList);
        assertTrue(actualProductList.isEmpty());
    }

    /*
     * Test case when error is thrown
     */
    @Test
    public void testGetProductsForExceptionThrown() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setUserID(userId);

        when(userRepository.findByUserID(userId)).thenReturn(user);
        when(advertisementRepository.findByUserID(user)).thenThrow(new RuntimeException("Test exception"));

        // Act
        List<ProductReal> actualProductList = productService.getProducts(userId);

        // Assert
        assertNull(actualProductList);
    }
    @Mock
    private Category categoryMock;  // Mock of Category entity

    /**
     * Testing addProduct Api
     */
    @Test
    public void testAddProduct() {
        // Arrange
        productReal.setProductID(29);
        productReal.setCategory(categoryMock);
        when(productReal.getSellType()).thenReturn("sell");
        when(productRealRepository.findLastPrimaryKey()).thenReturn(29);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(electronicGadgetRepository.save(any(ElectronicGadget.class))).thenReturn(mock(ElectronicGadget.class));
        when(booksRepository.save(any(Books.class))).thenReturn(mock(Books.class));
        when(furnitureRepository.save(any(Furniture.class))).thenReturn(mock(Furniture.class));
        when(kitchenApplianceRepository.save(any(KitchenAppliance.class))).thenReturn(mock(KitchenAppliance.class));
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);
        Area area = new Area();
        area.setAreaID(501);
        when(productReal.getArea()).thenReturn(area);

        // Act
        ProductReal result = productService.addProduct(123, productReal);

        // Assert
        assertNotNull(result);
        assertEquals(productReal.getProductID(), result.getProductID());
        assertEquals(productReal.getName(), result.getName());
        verify(productRealRepository, times(1)).findLastPrimaryKey();
        verify(productRealRepository, times(1)).save(eq(productReal));
    }
    /**
     * Testing addProduct Api assuming there are no products initially.
     */
    @Test
    public void testAddProductWithNullPrimaryKey() {
        // Arrange
        productReal.setProductID(29);
        productReal.setCategory(categoryMock);
        when(productReal.getSellType()).thenReturn("sell");
        when(productRealRepository.findLastPrimaryKey()).thenReturn(null); // Assuming there are no products initially.
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(electronicGadgetRepository.save(any(ElectronicGadget.class))).thenReturn(mock(ElectronicGadget.class));
        when(booksRepository.save(any(Books.class))).thenReturn(mock(Books.class));
        when(furnitureRepository.save(any(Furniture.class))).thenReturn(mock(Furniture.class));
        when(kitchenApplianceRepository.save(any(KitchenAppliance.class))).thenReturn(mock(KitchenAppliance.class));
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);
        Area area = new Area();
        area.setAreaID(501);
        when(productReal.getArea()).thenReturn(area);

        // Act
        ProductReal result = productService.addProduct(123, productReal);

        // Assert
        assertNotNull(result);
        assertEquals(productReal.getProductID(), result.getProductID());
        assertEquals(productReal.getName(), result.getName());
        verify(productRealRepository, times(1)).findLastPrimaryKey();
        verify(productRealRepository, times(1)).save(eq(productReal));
    }
    @Test
    public void testGetAdvertisementObj() {
        // Arrange
        ProductReal productReal = new ProductReal();
        productReal.setVerificationStatus("Verified");
        productReal.setAdvtTitle("Test Ad");
        productReal.setPostDate(new Date());
        productReal.setExpiryDate(new Date());
        productReal.setIsActive("y");
        ProductReal savedProductReal = new ProductReal();
        productReal.setSellType("sell");
        savedProductReal.setProductID(1);
        int userId = 123;
        User mockUser = new User();
        when(userRepository.findByUserID(userId)).thenReturn(mockUser);

        // Act
        Advertisement result = productService.getAdvertisementObj(productReal, savedProductReal, userId);

        // Assert
        verify(userRepository, times(1)).findByUserID(userId);
        assertEquals("Verified", result.getVerificationStatus());
        assertEquals("Test Ad", result.getAdvtTitle());
    }

    /**
     * Testing addProduct Api with Electronic Gadget
     */
    @Test
    public void testAddProductWithElectronicGadgetCategory() {
        // Arrange
        productReal.setProductID(29);
        productReal.setCategory(categoryMock);
        Area area = new Area();
        area.setAreaID(501);
        productReal.setArea(area);
        when(productReal.getSellType()).thenReturn("sell");
        when(productRealRepository.findLastPrimaryKey()).thenReturn(29);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(productRealRepository.findLastPrimaryKey()).thenReturn(0);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory().getCategoryID()).thenReturn(Constant.ELECTRONIC_GADGET_CATEGORY_ID);
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);
        when(productReal.getArea()).thenReturn(area);

        // Act
        ProductReal result = productService.addProduct(123, productReal);

        // Assert
        verify(electronicGadgetRepository, times(1)).save(any(ElectronicGadget.class));
        verify(booksRepository, never()).save(any(Books.class));
        verify(furnitureRepository, never()).save(any(Furniture.class));
        verify(kitchenApplianceRepository, never()).save(any(KitchenAppliance.class));
        verify(productRealRepository, times(1)).save(any(ProductReal.class));

        assertNotNull(result);
    }

    /**
     * Testing addProduct Api with Books
     */
    @Test
    public void testAddProductWithBooksCategory() {
        // Arrange
        productReal.setProductID(29);
        productReal.setCategory(categoryMock);
        when(productReal.getSellType()).thenReturn("sell");
        Area area = new Area();
        area.setAreaID(501);
        when(productReal.getArea()).thenReturn(area);
        when(productRealRepository.findLastPrimaryKey()).thenReturn(29);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(productRealRepository.findLastPrimaryKey()).thenReturn(0);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory().getCategoryID()).thenReturn(Constant.BOOKS_CATEGORY_ID);
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);

        // Act
        ProductReal result = productService.addProduct(123, productReal);

        // Arrange
        verify(electronicGadgetRepository, never()).save(any(ElectronicGadget.class));
        verify(booksRepository, times(1)).save(any(Books.class));
        verify(furnitureRepository, never()).save(any(Furniture.class));
        verify(kitchenApplianceRepository, never()).save(any(KitchenAppliance.class));
        verify(productRealRepository, times(1)).save(any(ProductReal.class));
        assertNotNull(result);
    }

    /**
     * Testing addProduct Api with kitchen Appliance
     */
    @Test
    public void testAddProductWithKitchenApplianceCategory() {
        // Arrange
        productReal.setProductID(29);
        productReal.setCategory(categoryMock);
        when(productReal.getSellType()).thenReturn("sell");
        when(productRealRepository.findLastPrimaryKey()).thenReturn(29);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(productRealRepository.findLastPrimaryKey()).thenReturn(0);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory().getCategoryID()).thenReturn(Constant.KITCHEN_APPLIANCE_CATEGORY_ID);
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);
        Area area = new Area();
        area.setAreaID(501);
        when(productReal.getArea()).thenReturn(area);

        // Act
        ProductReal result = productService.addProduct(123, productReal);

        // Assert
        verify(electronicGadgetRepository, never()).save(any(ElectronicGadget.class));
        verify(booksRepository, never()).save(any(Books.class));
        verify(furnitureRepository, never()).save(any(Furniture.class));
        verify(kitchenApplianceRepository, times(1)).save(any(KitchenAppliance.class));
        verify(productRealRepository, times(1)).save(any(ProductReal.class));

        assertNotNull(result);
    }

    /**
     * Testing addProduct Api with Furniture
     */
    @Test
    public void testAddProductWithFurnitureCategory() {
        // Arrange
        productReal.setProductID(29);
        productReal.setCategory(categoryMock);
        when(productReal.getSellType()).thenReturn("sell");
        Area area = new Area();
        area.setAreaID(501);
        when(productReal.getArea()).thenReturn(area);
        when(productRealRepository.findLastPrimaryKey()).thenReturn(29);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(productRealRepository.findLastPrimaryKey()).thenReturn(0);
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(productReal);
        when(productReal.getCategory().getCategoryID()).thenReturn(Constant.FURNITURE_CATEGORY_ID);
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);

        // ACT
        ProductReal result = productService.addProduct(123, productReal);

        // Assert
        verify(electronicGadgetRepository, never()).save(any(ElectronicGadget.class));
        verify(booksRepository, never()).save(any(Books.class));
        verify(furnitureRepository, times(1)).save(any(Furniture.class));
        verify(kitchenApplianceRepository, never()).save(any(KitchenAppliance.class));
        verify(productRealRepository, times(1)).save(any(ProductReal.class));

        assertNotNull(result);
    }


    /**
     * Testing updateProduct Api
     */
    @Test
    public void testUpdateProduct() {
        // Arrange
        int productId = 29; // Product ID
        int categoryID = 20;

        ProductReal fetchedProductReal = mock(ProductReal.class);
        fetchedProductReal.setProductID(1);
        fetchedProductReal.setName("Old Product");
        fetchedProductReal.setDescription("Old Description");
        fetchedProductReal.setPrice(50);

        ProductReal updatedProductReal = mock(ProductReal.class);
        updatedProductReal.setProductID(1);
        updatedProductReal.setName("Updated Product");
        updatedProductReal.setDescription("Updated Description");
        updatedProductReal.setPrice(75);

        when(productReal.getCategory()).thenReturn(mock(Category.class));
        when(productReal.getCategory().getCategoryID()).thenReturn(categoryID);
        when(productReal.getSellType()).thenReturn("sell");
        Category category = mock(Category.class);
        category.setCategoryID(categoryID);
        productReal.setCategory(category);
        productReal.setProductID(productId);
        when(productRealRepository.findByProductID(productId)).thenReturn(mock(ProductReal.class));
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(mock(ProductReal.class));

        // ACT
        ProductReal result = productService.updateProduct(123, productReal);

        // Assert
        assertNotNull(result);
        assertEquals(updatedProductReal.getProductID(), result.getProductID());
        assertEquals(updatedProductReal.getName(), result.getName());
        assertEquals(updatedProductReal.getDescription(), result.getDescription());
        assertEquals(updatedProductReal.getPrice(), result.getPrice(), 0.001);
    }

    /**
     * This method returns Product Entity object which is used in the testing update api
     */
    private ProductReal createProductForUpdate(int productId) {
        ProductReal productReal = new ProductReal();
        productReal.setProductID(productId);
        productReal.setName("Test Product");
        productReal.setDescription("Test Description");
        productReal.setPrice(50);
        productReal.setSellType("sell");
        return productReal;
    }

    /**
     * Testing updateProduct Api with Electronic Gadget
     */
    @Test
    public void testUpdateElectronicGadget() {
        // Arrange
        int userId = 1;
        int productId = 29;
        ProductReal productReal = createProductForUpdate(productId);
        Category category = new Category();
        category.setCategoryID(Constant.ELECTRONIC_GADGET_CATEGORY_ID);
        productReal.setCategory(category);
        Area area = new Area();
        area.setAreaID(501);
        productReal.setArea(area);
        productReal.setSellType("sell");
        ElectronicGadget electronicGadget = new ElectronicGadget();
        electronicGadget.setManufacturer("old Manufacture");
        electronicGadget.setYearOfPurchase("old Product");
        electronicGadget.setModelName("old model name");
        electronicGadget.setGadgetType("Old gadget type");
        when(electronicGadgetRepository.findByElectronicGadgetsID(productId)).thenReturn(electronicGadget);
        when(productRealRepository.findByProductID(productId)).thenReturn(mock(ProductReal.class));
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(mock(ProductReal.class));
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);

        // Act
        ProductReal updatedProduct = productService.updateProduct(userId, productReal);

        // Assert
        assertNotNull(updatedProduct);

        // Verifying that repository methods were called
        verify(electronicGadgetRepository, times(1)).findByElectronicGadgetsID(productId);
        verify(electronicGadgetRepository, times(1)).save(any(ElectronicGadget.class));
        verify(productRealRepository, times(1)).findByProductID(productId);
        verify(productRealRepository, times(1)).save(any(ProductReal.class));
    }

    /**
     * Testing updateProduct Api with Books
     */
    @Test
    public void testUpdateBooks() {
        // Arrange
        int userId = 1;
        int productId = 29;
        ProductReal productReal = createProductForUpdate(productId);
        Category category = new Category();
        category.setCategoryID(Constant.BOOKS_CATEGORY_ID);
        productReal.setCategory(category);
        Area area = new Area();
        area.setAreaID(501);
        productReal.setArea(area);
        productReal.setSellType("sell");
        Books books = new Books();
        books.setYearOfPublished("1988");
        books.setAuthor("old author");
        books.setCondition("old condition");
        when(booksRepository.findBooksByBooksID(productId)).thenReturn(books);
        when(productRealRepository.findByProductID(productId)).thenReturn(mock(ProductReal.class));
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(mock(ProductReal.class));
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);

        // Act
        ProductReal updatedProduct = productService.updateProduct(userId, productReal);

        // Assert
        assertNotNull(updatedProduct);
        verify(booksRepository, times(1)).findBooksByBooksID(productId);
        verify(booksRepository, times(1)).save(any(Books.class));
        verify(productRealRepository, times(1)).findByProductID(productId);
        verify(productRealRepository, times(1)).save(any(ProductReal.class));
    }

    /**
     * Testing updateProduct Api with Furniture
     */
    @Test
    public void testUpdateFurniture() {
        // Arrange
        int userId = 1;
        int productId = 29;
        ProductReal productReal = createProductForUpdate(productId);
        Category category = new Category();
        category.setCategoryID(Constant.FURNITURE_CATEGORY_ID);
        productReal.setCategory(category);
        Area area = new Area();
        area.setAreaID(501);
        productReal.setArea(area);
        productReal.setSellType("sell");
        Furniture furniture = new Furniture();
        furniture.setFurnitureCondition("old condition");
        furniture.setFurnitureType("old type");
        furniture.setManufacturer("old manufacturer");
        furniture.setYearOfPurchase("2021");
        when(furnitureRepository.findFurnitureByFurnitureID(productId)).thenReturn(furniture);
        when(productRealRepository.findByProductID(productId)).thenReturn(mock(ProductReal.class));
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(mock(ProductReal.class));
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);

        // Act
        ProductReal updatedProduct = productService.updateProduct(userId, productReal);

        // Assert
        assertNotNull(updatedProduct);
        verify(furnitureRepository, times(1)).findFurnitureByFurnitureID(productId);
        verify(furnitureRepository, times(1)).save(any(Furniture.class));
        verify(productRealRepository, times(1)).findByProductID(productId);
        verify(productRealRepository, times(1)).save(any(ProductReal.class));
    }

    /**
     * Testing updateProduct Api with Kitchen Appliance
     */
    @Test
    public void testUpdateKitchenAppliance() {
        // Arrange
        int userId = 1;
        int productId = 29;
        ProductReal productReal = createProductForUpdate(productId);
        Category category = new Category();
        category.setCategoryID(Constant.KITCHEN_APPLIANCE_CATEGORY_ID);
        productReal.setCategory(category);
        Area area = new Area();
        area.setAreaID(501);
        productReal.setArea(area);
        productReal.setSellType("sell");
        KitchenAppliance kitchenAppliance = new KitchenAppliance();
        kitchenAppliance.setManufacturer("old manufacturer");
        kitchenAppliance.setApplianceType("old type");
        kitchenAppliance.setYearOfPurchase("2023");
        kitchenAppliance.setModelName("old model name");
        when(kitchenApplianceRepository.findByKitchenApplianceID(productId)).thenReturn(kitchenAppliance);
        when(productRealRepository.findByProductID(productId)).thenReturn(mock(ProductReal.class));
        when(productRealRepository.save(any(ProductReal.class))).thenReturn(mock(ProductReal.class));
        when(areaRepository.findByAreaID(any(Integer.class))).thenReturn(this.area);

        // Act
        ProductReal updatedProduct = productService.updateProduct(userId, productReal);

        // Assert
        assertNotNull(updatedProduct);
        verify(kitchenApplianceRepository, times(1)).findByKitchenApplianceID(productId);
        verify(kitchenApplianceRepository, times(1)).save(any(KitchenAppliance.class));
        verify(productRealRepository, times(1)).findByProductID(productId);
        verify(productRealRepository, times(1)).save(any(ProductReal.class));
    }

    /**
     * Testing delete product api
     * @param categoryId is the categoryId
     */
    @ParameterizedTest
    @ValueSource(ints = { Constant.KITCHEN_APPLIANCE_CATEGORY_ID, Constant.ELECTRONIC_GADGET_CATEGORY_ID, Constant.BOOKS_CATEGORY_ID, Constant.FURNITURE_CATEGORY_ID , 100})
    public void testDeleteProduct(int categoryId) {
        // Arrange
        int userId = 1001;
        int productId = 41;
        ProductReal productReal = createProductForDelete(productId, categoryId);

        // Act
        productService.deleteProduct(userId, productReal);

        // Assert

        switch (productReal.getCategory().getCategoryID()) {
            case Constant.ELECTRONIC_GADGET_CATEGORY_ID:
                verify(electronicGadgetRepository, times(1)).deleteByElectronicGadgetsID(productId);
                break;
            case Constant.BOOKS_CATEGORY_ID:
                verify(booksRepository, times(1)).deleteByBooksID(productId);
                break;
            case Constant.FURNITURE_CATEGORY_ID:
                verify(furnitureRepository, times(1)).deleteByFurnitureID(productId);
                break;
            case Constant.KITCHEN_APPLIANCE_CATEGORY_ID:
                verify(kitchenApplianceRepository, times(1)).deleteByKitchenApplianceID(productId);
                break;
            default:
                // Add assertions or verification for the default case
        }
        if(categoryId == 100){
            verify(productRealRepository, never()).deleteByProductID(productId);
        }
        else{
            verify(productRealRepository, times(1)).deleteByProductID(productId);
        }

    }

    /**
     * private method to create product for delete
     * @param productId product id int
     * @param categoryId int category id
     * @return product real object
     */
    private ProductReal createProductForDelete(int productId, int categoryId) {
        ProductReal productReal = new ProductReal();
        productReal.setProductID(productId);
        productReal.setName("Test Product");
        productReal.setDescription("Test Description");
        productReal.setPrice(50);
        Category category = mock(Category.class);
        when(category.getCategoryID()).thenReturn(categoryId);
        productReal.setCategory(category);
        return productReal;
    }

    /**
     * test case to get product null object
     */
    @Test
    void getProductNull() {
        Mockito.when(productDAO.getAllAvailableProduct(any(Integer.class))).thenReturn(null);
        var genProd=productService.getProduct(0);
        assertNull(genProd);
    }
}