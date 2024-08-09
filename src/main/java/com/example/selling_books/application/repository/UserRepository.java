package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.User;
import com.example.selling_books.application.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u")
    Page<User> queryUsers(Pageable pageable);

}
