package com.rentx.businessservices;


import com.rentx.businessservices.interfaces.IProductService;
import com.rentx.config.Constant;
import com.rentx.dataaccess.interfaces.IProductDAO;
import com.rentx.dataaccess.repository.*;
import com.rentx.dtos.ProductDto;
import com.rentx.entities.*;
import com.rentx.mappers.ProductMapper;
import com.rentx.utils.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Product business service class implementation
 */
@RequiredArgsConstructor
@Service
@Transactional
public class ProductService implements IProductService {

    IProductDAO productDAO;
    UserRepository userRepository;
    ProductRealRepository productRealRepository;
    ElectronicGadgetRepository electronicGadgetRepository;
    BooksRepository booksRepository;
    KitchenApplianceRepository kitchenApplianceRepository;
    FurnitureRepository furnitureRepository;
    AdvertisementService advertisementService;
    AdvertisementRepository advertisementRepository;
    AreaRepository areaRepository;

    /**
     * Constructor of product service class
     *
     * @param productDAO product data access object
     */
    @Autowired
    public ProductService(IProductDAO productDAO, UserRepository userRepository,
                          ProductRealRepository productRealRepository,
                          ElectronicGadgetRepository electronicGadgetRepository,
                          BooksRepository booksRepository,
                          KitchenApplianceRepository kitchenApplianceRepository,
                          FurnitureRepository furnitureRepository, AdvertisementService advertisementService,
                          AdvertisementRepository advertisementRepository,
                          AreaRepository areaRepository) {
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.productRealRepository = productRealRepository;
        this.electronicGadgetRepository = electronicGadgetRepository;
        this.booksRepository = booksRepository;
        this.kitchenApplianceRepository = kitchenApplianceRepository;
        this.furnitureRepository = furnitureRepository;
        this.advertisementService = advertisementService;
        this.advertisementRepository = advertisementRepository;
        this.areaRepository = areaRepository;
    }

    /**
     * get all the available products
     *
     * @return product dtos
     */
    @Override
    public List<ProductDto> getAllAvailableProducts() {
        return ProductMapper.toProductDtos(productDAO.getAllAvailableProducts());
    }

    /**
     * get a product by id
     *
     * @param productId product id to get
     * @return product dto
     */
    @Override
    public ProductDto getProduct(int productId) {
        return ProductMapper.toProductDto(productDAO.getAllAvailableProduct(productId));
    }

    /**
     * Returns list of product linked with the user
     *
     * @param userId is usedID
     * @return List if Product entity
     */
    @Override
    public List<ProductReal> getProducts(int userId) {
        try {
            User user = userRepository.findByUserID(userId);
            List<Advertisement> advertisementList = advertisementRepository.findByUserID(user);
            List<ProductReal> productRealList = new ArrayList<>();
            for (Advertisement ad : advertisementList) {
                productRealList.add(ad.getProductID());
            }
            return productRealList;
        } catch (Exception e) {
            System.err.println("Error in getProducts: " + e.getMessage()); // Print the error message
            return null;
        }
    }

    /**
     * Add product
     *
     * @param userId      is the userId
     * @param productReal is the product entity
     * @return product entity
     */
    @Override
    public ProductReal addProduct(int userId, ProductReal productReal) {
        int lastID;
        try{
            lastID = productRealRepository.findLastPrimaryKey();
        }
        catch (NullPointerException  e){
            lastID = 1;
        }
        productReal.setProductID(lastID + 1);
        switch (productReal.getCategory().getCategoryID()) {
            case Constant.ELECTRONIC_GADGET_CATEGORY_ID:
                ElectronicGadget electronicGadget =
                        UserServiceUtils.getElectronicGadget(productReal);
                electronicGadgetRepository.save(electronicGadget);
                break;
            case Constant.BOOKS_CATEGORY_ID:
                Books books = UserServiceUtils.getBooks(productReal);
                booksRepository.save(books);
                break;
            case Constant.FURNITURE_CATEGORY_ID:
                Furniture furniture = UserServiceUtils.getFurniture(productReal);
                furnitureRepository.save(furniture);
                break;
            case Constant.KITCHEN_APPLIANCE_CATEGORY_ID:
                KitchenAppliance kitchenAppliance =
                        UserServiceUtils.getKitchenAppliance(productReal);
                kitchenApplianceRepository.save(kitchenAppliance);
                break;
            default:
                System.out.println("No products category found for: " + productReal.getProductID());
        }
        Area updatedArea = areaRepository.findByAreaID(productReal.getArea().getAreaID());
        productReal.setArea(updatedArea);
        ProductReal savedProductReal = productRealRepository.save(productReal);
        productReal.setProductID(savedProductReal.getProductID());
        saveAdvertisement(productReal, savedProductReal, userId);
        return productReal;
    }

    /**
     * @param productReal      is Product entity
     * @param savedProductReal is newly created product entity
     * @param userId           is userId
     */
    public void saveAdvertisement(ProductReal productReal, ProductReal savedProductReal, int userId) {
        Advertisement advertisement = getAdvertisementObj(productReal, savedProductReal, userId);
        advertisementService.addAdvertisement(advertisement);
    }

    /**
     * Creates advertisement object
     *
     * @param productReal      is Product entity
     * @param savedProductReal is newly created product entity
     * @param userId           is userId
     * @return Advertisement object
     */
    public Advertisement getAdvertisementObj(ProductReal productReal, ProductReal savedProductReal, int userId) {
        Advertisement advertisement = new Advertisement();
        advertisement.setVerificationStatus(productReal.getVerificationStatus());
        advertisement.setAdvtTitle(productReal.getAdvtTitle());
        Date currentDate = getCurrentDate();
        Date updatedDate = getExpiryDate(currentDate);
        advertisement.setPostDate(currentDate);
        advertisement.setExpiryDate(updatedDate);
        advertisement.setIsActive(getIsActive());
        advertisement.setProductID(savedProductReal);
        User user = userRepository.findByUserID(userId);
        advertisement.setUserID(user);
        return advertisement;
    }

    private static String getIsActive() {
        return "y";
    }

    private static Date getExpiryDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 1); // Add one year
        return calendar.getTime();
    }

    private static Date getCurrentDate() {
        return new Date();
    }

    /**
     * update product helper
     *
     * @param productReal product real
     * @param productId   product id
     * @return product found or not
     */
    private boolean updateProductHelper(ProductReal productReal, int productId) {
        switch (productReal.getCategory().getCategoryID()) {
            case Constant.ELECTRONIC_GADGET_CATEGORY_ID:
                ElectronicGadget electronicGadget =
                        electronicGadgetRepository.findByElectronicGadgetsID(productId);
                if (electronicGadget != null) {
                    ElectronicGadget updatedElectronicGadget =
                            UserServiceUtils.setElectronicGadget(productReal, electronicGadget);
                    electronicGadgetRepository.save(updatedElectronicGadget);
                }
                break;
            case Constant.BOOKS_CATEGORY_ID:
                Books books = booksRepository.findBooksByBooksID(productId);
                if (books != null) {
                    Books updatedBooks = UserServiceUtils.setBooks(productReal, books);
                    booksRepository.save(updatedBooks);
                }
                break;
            case Constant.FURNITURE_CATEGORY_ID:
                Furniture furniture = furnitureRepository.findFurnitureByFurnitureID(productId);
                if (furniture != null) {
                    Furniture updatedFurniture =
                            UserServiceUtils.setFurniture(productReal, furniture);
                    furnitureRepository.save(updatedFurniture);
                }
                break;
            case Constant.KITCHEN_APPLIANCE_CATEGORY_ID:
                KitchenAppliance kitchenAppliance =
                        kitchenApplianceRepository.findByKitchenApplianceID(productId);
                if (kitchenAppliance != null) {
                    KitchenAppliance updatedKitchenAppliance =
                            UserServiceUtils.setKitchenAppliance(productReal, kitchenAppliance);
                    kitchenApplianceRepository.save(updatedKitchenAppliance);
                }
                break;
            default:
                System.out.println("No products category found for: " + productReal.getProductID());
                return false;
        }
        return true;
    }

    /**
     * Updates product details
     *
     * @param userId      is userID
     * @param productReal takes product entity
     * @return product entity
     */
    @Override
    public ProductReal updateProduct(int userId, ProductReal productReal) {
        int productId = productReal.getProductID();

        if (updateProductHelper(productReal, productId)) {

            Area updatedArea = areaRepository.findByAreaID(productReal.getArea().getAreaID());
            productReal.setArea(updatedArea);
            ProductReal fetchedProductReal = productRealRepository.findByProductID(productId);
            ProductReal updatedProductReal =
                    UserServiceUtils.setProduct(fetchedProductReal, productReal);
            productRealRepository.save(updatedProductReal);
            return updatedProductReal;

        } else
            return productReal;
    }

    /**
     * Delete product from the table
     *
     * @param userID      is userID
     * @param productReal is Product
     * @return number of rows deleted
     */
    @Override
    public int deleteProduct(int userID, ProductReal productReal) {
        int productId = productReal.getProductID();
        int categoryId = productReal.getCategory().getCategoryID();
        switch (categoryId) {
            case Constant.ELECTRONIC_GADGET_CATEGORY_ID:
                electronicGadgetRepository.deleteByElectronicGadgetsID(productId);
                break;
            case Constant.BOOKS_CATEGORY_ID:
                booksRepository.deleteByBooksID(productId);
                break;
            case Constant.FURNITURE_CATEGORY_ID:
                furnitureRepository.deleteByFurnitureID(productId);
                break;
            case Constant.KITCHEN_APPLIANCE_CATEGORY_ID:
                kitchenApplianceRepository.deleteByKitchenApplianceID(productId);
                break;
            default:
                System.out.println("No products category found for: " + productReal.getProductID());
                return 1;
        }
        advertisementRepository.deleteByProductID(productReal);
        productRealRepository.deleteByProductID(productId);
        return 1;
    }
}