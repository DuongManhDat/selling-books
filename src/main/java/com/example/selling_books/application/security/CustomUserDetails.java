package com.example.selling_books.application.security;

import com.example.selling_books.application.entity.Role;
import com.example.selling_books.application.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private User user;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.isLocked();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
