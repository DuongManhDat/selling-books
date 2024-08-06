package com.example.selling_books.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookImage {

    public static final int MAXIMUM_IMAGES_PER_PRODUCT = 6;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="book_id")
    @JsonIgnore
    Book book;

    @Column(name="image_url", length = 300)
    @JsonProperty("image_url")
    String imageUrl;
}
