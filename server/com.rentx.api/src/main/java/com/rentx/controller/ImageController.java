package com.rentx.controller;

import com.rentx.businessservices.interfaces.IImageService;
import com.rentx.dtos.ImageDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.io.IOException;

/**
 * Image controller class
 */
@Getter
@Setter
@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @Value("${gcp.bucket.name}")
    private String bucketName;

    /**
     * api to upload the image file
     *
     * @param file file to upload
     * @param id   id of the file
     * @param type file type (user/profile)
     * @return status of the upload
     * @throws IOException IO exception
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, @RequestParam Integer id, @RequestParam String type) throws IOException {
        if (imageService.uploadFile(bucketName, file, id, type))
            return ResponseEntity.ok("File uploaded successfully");
        else return ResponseEntity.ok("Error while Uploading");
    }

    /**
     * download the file
     *
     * @param fileName file to download
     * @return return the file
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam String fileName) {

        ByteArrayResource resource = imageService.downloadFile(bucketName, fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"");

        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_OCTET_STREAM).
                headers(headers).body(resource);
    }

    /**
     * get the file details
     *
     * @param type file type
     * @param id   file id
     * @return image details
     */
    @GetMapping("/details")
    public ResponseEntity<List<ImageDto>> getFileDetails(
            @RequestParam String type,
            @RequestParam Integer id) {

        List<ImageDto> imageDetails = imageService.get(type, id);

        return ResponseEntity.ok(imageDetails);
    }
}
