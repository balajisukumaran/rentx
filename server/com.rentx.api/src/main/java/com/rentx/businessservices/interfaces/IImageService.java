package com.rentx.businessservices.interfaces;

import com.rentx.dtos.ImageDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * interface to image service class
 */
public interface IImageService {
    /**
     * method to download file
     * @param bucketName string bucket name
     * @param name string name
     * @return byte array resource
     */
    ByteArrayResource downloadFile(String bucketName, String name);

    /**
     * method for upload file
     * @param bucketName string bucket name
     * @param file multipart file
     * @param id for id
     * @param type string type
     * @return boolean file
     * @throws IOException exception in code
     */
    boolean uploadFile(String bucketName, MultipartFile file, Integer id, String type) throws IOException;

    /**
     * method for get file
     * @param type string type of file
     * @param id for id
     * @return list of image dto
     */
    List<ImageDto> get(String type, Integer id);
}