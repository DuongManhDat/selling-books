package com.example.selling_books.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @Column(name="full_name", length = 100)
    String fullName;

    @Column(name = "email", length = 100)
    String email;

    @Column(name = "phone_number",nullable = false, length = 100)
    String phoneNumber;

    @Column(name="order_date")
    LocalDate orderDate;

    @Column(name="status")
    String status;

    @Column(name="address", length = 100)
    String address;

    @Column(name = "note", length = 100)
    String note;

    @Column(name="total_price")
    Float totalPrice;

    @Column(name="payment_method")
    String payment_method;

    @Column(name="shipping_fee")
    Float shippingFee;

    @Column(name="shipping_address")
    String shippingAdress;

    @Column(name="payment_status")
    String paymentStatus;

    @Column(name = "active")
    Boolean active;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OderItem> oderItems;
}
