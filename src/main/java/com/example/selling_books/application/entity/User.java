package com.example.selling_books.application.entity;

import com.example.selling_books.application.utils.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="full_name")
    String fullName;

    @Column(name="password")
    String password;

    @Column(name="email")
    String email;

    @Column(name="phone_number")
    String phoneNumber;

    @Column(name="address")
    String address;

    @ManyToOne
    @JoinColumn(name="role_id")
    Role role;

    @Column(name="gender")
    Gender gender;

    @Column(name="date_of_birth")
    Date dateOfBirth;

    @Column(name="is_locked")
    boolean isLocked;

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Review> reviews;


}
