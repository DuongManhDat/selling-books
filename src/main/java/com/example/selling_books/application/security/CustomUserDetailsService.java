package com.example.selling_books.application.security;

import com.example.selling_books.application.entity.User;
import com.example.selling_books.application.repository.UserRepository;
import com.example.selling_books.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}
