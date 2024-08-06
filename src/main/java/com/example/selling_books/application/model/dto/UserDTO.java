package com.example.selling_books.application.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends BaseEnityDTO {

    Long id;
    String fullName;
    String email;
    String address;
    String phoneNumber;
    boolean isLocked;
    List<String> roles;
}
