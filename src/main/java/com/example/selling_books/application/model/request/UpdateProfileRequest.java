package com.example.selling_books.application.model.request;

import com.example.selling_books.application.entity.Review;
import com.example.selling_books.application.entity.Role;
import com.example.selling_books.application.utils.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProfileRequest {

    @NotBlank(message = "Họ tên trống")
    String fullName;

    @Email(message = "Email không đúng định dạng")
    String email;

    @Pattern(regexp="(84|0[3|5|7|8|9])+([0-9]{8})\\b",message = "Số điện thoại không hợp lệ!")
    String phoneNumber;

    String address;

    String gender;

    String dateOfBirth;
}
