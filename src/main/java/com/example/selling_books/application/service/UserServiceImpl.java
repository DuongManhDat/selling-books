package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.User;
import com.example.selling_books.application.exception.BadRequestException;
import com.example.selling_books.application.model.dto.UserDTO;
import com.example.selling_books.application.model.request.ChangePasswordRequest;
import com.example.selling_books.application.model.request.CreateUserRequest;
import com.example.selling_books.application.model.request.UpdateProfileRequest;
import com.example.selling_books.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<UserDTO> getListUser(PageRequest pageRequest) {
        Page<User> users = userRepository.queryUsers(pageRequest);
        return users.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO createUser(CreateUserRequest createUserRequest) {
        Optional<User> user = userRepository.findByEmail(createUserRequest.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }
        User newUser = modelMapper.map(createUserRequest, User.class);
        newUser.setLocked(false);
        userRepository.save(newUser);
        return modelMapper.map(newUser, UserDTO.class);
    }

    @Override
    public UserDTO updateProfile(Long id, UpdateProfileRequest updateProfileRequest) {
        User newUser = modelMapper.map(updateProfileRequest,User.class);
        newUser.setId(id);
        userRepository.save(newUser);
        return modelMapper.map(newUser, UserDTO.class);
    }

    @Override
    public void changePassword(Long id, ChangePasswordRequest changePasswordRequest) {

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
