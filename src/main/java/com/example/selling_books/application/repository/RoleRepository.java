package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
