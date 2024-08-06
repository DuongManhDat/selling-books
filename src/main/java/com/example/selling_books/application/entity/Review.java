package com.example.selling_books.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="content")
    String content;

    @Column(name="rating")
    String rating;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="book_id")
    Book book;
}
