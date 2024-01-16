package com.rentx.utils;

import com.rentx.entities.*;

public class UserServiceUtils {
    /**
     * mstatic method to get Electronic Gadget
     * @param productReal product Real
     * @return object of electronic gadget
     */
    public static ElectronicGadget getElectronicGadget(ProductReal productReal) {
        ElectronicGadget electronicGadget = new ElectronicGadget();
        electronicGadget.setElectronicGadgetsID(productReal.getProductID());
        electronicGadget.setCategoryID(productReal.getCategory());
        electronicGadget.setManufacturer(productReal.getManufacture());
        electronicGadget.setYearOfPurchase(productReal.getYear_of_purchase());
        electronicGadget.setModelName(productReal.getModel_name());
        electronicGadget.setGadgetType(productReal.getGadget_type());
        return electronicGadget;
    }

    /**
     * static method to set Electronic Gadget
     * @param productReal  product Real
     * @param electronicGadget electronic Gadget
     * @return updated electronic Gadget
     */
    public static ElectronicGadget setElectronicGadget(ProductReal productReal, ElectronicGadget electronicGadget) {
        electronicGadget.setManufacturer(productReal.getManufacture());
        electronicGadget.setYearOfPurchase(productReal.getYear_of_purchase());
        electronicGadget.setModelName(productReal.getModel_name());
        electronicGadget.setGadgetType(productReal.getGadget_type());
        return electronicGadget;
    }

    /**
     * static method to get Books
     * @param productReal product Real
     * @return updated book object
     */
    public static Books getBooks(ProductReal productReal) {
        Books books = new Books();
        books.setBooksID(productReal.getProductID());
        books.setCategoryID(productReal.getCategory());
        books.setAuthor(productReal.getAuthor());
        books.setCondition(productReal.getCondition());
        books.setYearOfPublished(productReal.getYear_of_public());
        return books;
    }

    /**
     * static method to set Books
     * @param productReal product Real
     * @param books books
     * @return books object
     */
    public static Books setBooks(ProductReal productReal, Books books) {
        books.setAuthor(productReal.getAuthor());
        books.setCondition(productReal.getCondition());
        books.setYearOfPublished(productReal.getYear_of_public());
        return books;
    }

    /**
     * static method to get Furniture
     * @param productReal product Real
     * @return updated furniture object
     */
    public static Furniture getFurniture(ProductReal productReal) {
        Furniture furniture = new Furniture();
        furniture.setFurnitureID(productReal.getProductID());
        furniture.setFurnitureType(productReal.getFurniture_type());
        furniture.setManufacturer(productReal.getManufacture());
        furniture.setFurnitureCondition(productReal.getCondition());
        furniture.setYearOfPurchase(productReal.getYear_of_purchase());
        furniture.setCategoryID(productReal.getCategory());
        return furniture;
    }

    /**
     * static method to set Furniture with product real and furniture
     * @param productReal product Real
     * @param furniture furniture
     * @return furniture object
     */
    public static Furniture setFurniture(ProductReal productReal, Furniture furniture) {
        furniture.setManufacturer(productReal.getManufacture());
        furniture.setFurnitureCondition(productReal.getCondition());
        furniture.setFurnitureType(productReal.getFurniture_type());
        furniture.setYearOfPurchase(productReal.getYear_of_purchase());
        return furniture;
    }

    /**
     * satic method to get Kitchen Appliance
     * @param productReal productReal
     * @return kitchen appliance object
     */
    public static KitchenAppliance getKitchenAppliance(ProductReal productReal) {
        KitchenAppliance kitchenAppliance = new KitchenAppliance();
        kitchenAppliance.setKitchenApplianceID(productReal.getProductID());
        kitchenAppliance.setApplianceType(productReal.getAppliance_type());
        kitchenAppliance.setCategoryID(productReal.getCategory());
        kitchenAppliance.setModelName(productReal.getModel_name());
        kitchenAppliance.setYearOfPurchase(productReal.getYear_of_purchase());
        kitchenAppliance.setManufacturer(productReal.getManufacture());
        return kitchenAppliance;
    }

    /**
     * static method to set Kitchen Appliance
     * @param productReal product Real
     * @param kitchenAppliance kitchen Appliance
     * @return kitchen appliance
     */
    public static KitchenAppliance setKitchenAppliance(ProductReal productReal, KitchenAppliance kitchenAppliance) {
        kitchenAppliance.setApplianceType(productReal.getAppliance_type());
        kitchenAppliance.setModelName(productReal.getModel_name());
        kitchenAppliance.setYearOfPurchase(productReal.getYear_of_purchase());
        kitchenAppliance.setManufacturer(productReal.getManufacture());
        return kitchenAppliance;
    }

    /**
     * static method to set product
     * @param fetchedProductReal fetched Product Real
     * @param updatedProductReal updated Product Real
     * @return fetched real product obbject
     */
    public static ProductReal setProduct(ProductReal fetchedProductReal, ProductReal updatedProductReal){
        fetchedProductReal.setName(updatedProductReal.getName());
        fetchedProductReal.setDescription(updatedProductReal.getDescription());
        fetchedProductReal.setPrice(updatedProductReal.getPrice());
        fetchedProductReal.setArea(updatedProductReal.getArea());
        fetchedProductReal.setSellType(updatedProductReal.getSellType());
        return fetchedProductReal;
    }
}
