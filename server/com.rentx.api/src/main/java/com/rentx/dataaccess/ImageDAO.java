package com.rentx.dataaccess;

import com.rentx.dataaccess.interfaces.IImageDAO;
import com.rentx.entities.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImageDAO implements IImageDAO {
    /**
     * final Entity Manager
     */
    private final EntityManager entityManager;

    /**
     * autowire the image DAO
     *
     * @param entityManager entity Manager
     */
    @Autowired
    public ImageDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * method to find all list
     *
     * @return result list query
     */
    @Override
    public List<Image> findAll() {
        TypedQuery<Image> query = entityManager.createQuery("from Image", Image.class);

        return query.getResultList();
    }

    /**
     * method to insert image
     *
     * @param user user
     * @return user object
     */
    @Transactional
    @Override
    public Image insert(Image user) {
        entityManager.merge(user);
        return user;
    }

    /**
     * method to get the list of image
     *
     * @param type type
     * @param id   id
     * @return result from the image list
     */
    @Override
    public List<Image> get(String type, Integer id) {
        List<Image> images = findAll();

        if (images == null) {
            return null;
        }

        Optional<List<Image>> result = Optional.of(images.stream().filter(image -> filterImages(image, type, id)).collect(Collectors.toList()));

        if (result.stream().findAny().isPresent() && result.get().stream().count() > 0)
            return result.get();

        return null;
    }

    private boolean filterImages(Image image, String type, Integer id) {
        return Objects.equals(image.getImageType(), type)
                && Objects.equals(image.getId(), id);
    }
}
