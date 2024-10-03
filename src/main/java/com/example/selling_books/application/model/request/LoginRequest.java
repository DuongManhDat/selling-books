package com.example.selling_books.application.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "Yêu cầu nhập email")
    @Email(message = "Yêu cầu nhập đúng định dạng email")
    String email;

    @NotBlank(message = "Yêu cầu nhập mật khẩu")
    @Size(min = 7, message = "Mật khẩu cần nhiều hơn 7 kí tự")
    String password;
}
