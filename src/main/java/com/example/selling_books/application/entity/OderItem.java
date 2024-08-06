package com.example.selling_books.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @Column(name="price", nullable = false)
    Float price;

    @Column(name="quantity")
    int quantity;

    @Column(name="total_price")
    Float totalPrice;
}
