package com.example.selling_books.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name", nullable = false, length = 350)
    String name;

    @Column(name="author")
    String author;

    @Column(name="publisher")
    String publisher;

    @Column(name="publication_year")
    String publicationYear;

    @Column(name="description")
    String description;

    @Column(name="thumbnail", length = 300)
    String thumbnail;

    @Column(name="selling_price")
    Float sellingPrice;

    @Column(name="cost")
    Float cost;

    @Column(name="discount")
    Float discount;

    @Column(name="stock")
    int stock;

    @ManyToMany
    @JoinTable(
        name="categories_books",
        joinColumns = @JoinColumn(name="book_id"),
        inverseJoinColumns = @JoinColumn(name="category_id"))
    Set<Category> categories;

    @OneToMany(mappedBy ="book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Review> reviews;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OderItem> oderItems;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<BookImage> bookImages;
}
