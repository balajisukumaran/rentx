package com.rentx.businessservices;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.rentx.businessservices.interfaces.IImageService;
import com.rentx.dataaccess.interfaces.IImageDAO;
import com.rentx.dtos.ImageDto;
import com.rentx.entities.Image;
import com.rentx.mappers.ImageMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * image service class
 */
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    /**
     * autowire storage
     */
    @Autowired
    Storage storage;
    /**
     * autowire image dao
     */
    @Autowired
    IImageDAO imageDAO;

    /**
     * download the file
     *
     * @param bucketName bucket name
     * @param name       name of the file
     * @return file
     */
    @Override
    public ByteArrayResource downloadFile(String bucketName, String name) {

        Blob blob = storage.get(bucketName, name);
        ByteArrayResource resource = new ByteArrayResource(
                blob.getContent());

        return resource;
    }

    /**
     * upload the file
     *
     * @param bucketName bucket to upload
     * @param file       file
     * @param id         file id
     * @param type       file type
     * @return status
     */
    @Override
    public boolean uploadFile(String bucketName, MultipartFile file, Integer id, String type) {
        try {

            Image image = new Image(id, file.getOriginalFilename(), type);
            imageDAO.insert(image);
            BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).
                    setContentType(file.getContentType()).build();
            storage.create(blobInfo, file.getBytes());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * get the image detail
     *
     * @param type type of image
     * @param id   id tagged to the image
     * @return image details
     */
    @Override
    public List<ImageDto> get(String type, Integer id) {
        List<Image> images = imageDAO.get(type, id);
        if (images == null) {
            return null;
        }
        return ImageMapper.toImageDtos(imageDAO.get(type, id));
    }
}