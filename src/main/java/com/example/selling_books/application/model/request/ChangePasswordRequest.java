package com.example.selling_books.application.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {

    @NotBlank(message = "Mật khẩu cũ trống")
    @Size(min = 6, message="Mật khẩu phải nhiều hơn 6 kí tự")
    String oldPassword;

    @NotBlank(message = "Mật khẩu mới trống")
    @Size(min = 6, message="Mật khẩu phải nhiều hơn 6 kí tự")
    String newPassword;
}
