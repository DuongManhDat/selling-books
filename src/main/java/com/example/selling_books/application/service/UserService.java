package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.User;
import com.example.selling_books.application.model.dto.UserDTO;
import com.example.selling_books.application.model.request.ChangePasswordRequest;
import com.example.selling_books.application.model.request.CreateUserRequest;
import com.example.selling_books.application.model.request.UpdateProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    Page<UserDTO> getListUser(PageRequest pageRequest);
    UserDTO createUser(CreateUserRequest createUserRequest);

    UserDTO updateProfile(Long id, UpdateProfileRequest updateProfileRequest);

    void changePassword(Long id, ChangePasswordRequest changePasswordRequest);

    void deleteUserById(Long id);

}
