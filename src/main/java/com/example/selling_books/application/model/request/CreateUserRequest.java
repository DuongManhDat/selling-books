package com.example.selling_books.application.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NotBlank(message = "Họ tên trống")
    String fullName;

    @NotBlank(message = "Email trống")
    @Email(message = "Email không đúng định dạng")
    String email;

    @NotBlank(message = "Mật khẩu trống")
    @Size(min = 6,max = 20, message = "Mật khẩu phải chứa từ 6-20 ký tự")
    String password;

    @Pattern(regexp="(84|0[3|5|7|8|9])+([0-9]{8})\\b",message = "Số điện thoại không hợp lệ!")
    String phoneNumber;

}
