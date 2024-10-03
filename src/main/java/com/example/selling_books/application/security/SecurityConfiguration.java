package com.example.selling_books.application.security;

import com.example.selling_books.application.repository.UserRepository;
import com.example.selling_books.application.service.UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }
    @Bean
    public DaoAuthenticationProvider authProvider(
        PasswordEncoder passwordEncoder,
        UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
//        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }

//    @Bean
//    public AuthenticationSuccessHandler customSuccessHandler(){
//        return new CustomSuccessHandler();
//    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/v1/customer/**").hasRole("USER") // Yêu cầu quyền "USER"
                .anyRequest().permitAll() // Cho phép tất cả các URL khác
            )
            .formLogin((form) -> form
                .loginPage("/api/v1/customer/account/login") // Trang đăng nhập tùy chỉnh
                .permitAll() // Cho phép truy cập vào trang đăng nhập mà không cần xác thực
            )
            .logout((logout) -> logout.permitAll()) // Cho phép truy cập trang đăng xuất mà không cần xác thực
            .exceptionHandling((exceptions) -> exceptions
                .authenticationEntryPoint((request, response, authException) ->
                    response.sendRedirect("/api/v1/customer/account/login")) // Chuyển hướng đến trang đăng nhập khi không có quyền
            );

        return http.build();
    }

}
