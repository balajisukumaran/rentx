package com.rentx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="image")
public class Image {
    /**
     * image method
     * @param id int id
     * @param name string name
     * @param imageType string image type
     */
    public Image(Integer id, String name, String imageType) {
        this.id = id;
        this.name = name;
        this.imageType = imageType;
    }

    /**
     * primary key and auto generated key for image id
     */
    @Id
    @Column(name="imageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;
    /**
     * id column
     */
    @Column(name = "id")
    private Integer id;
    /**
     * string name of image
     */
    @Column(name = "name")
    private String name;
    /**
     * type of image
     */
    @Column(name = "image_type")
    private String imageType;

}
