package com.rentx.dataaccess.interfaces;

import com.rentx.entities.Image;
import java.util.List;

public interface IImageDAO {
    /**
     * method to find all
     * @return image object
     */
    List<Image> findAll();

    /**
     * method to insert the user image
     * @param user user
     * @return image object
     */
    Image insert(Image user);

    /**
     * method to get image by id and it's type
     * @param type type
     * @param id id
     * @return image object
     */
    List<Image> get(String type, Integer id);
}
