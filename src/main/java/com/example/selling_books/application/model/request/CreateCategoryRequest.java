package com.example.selling_books.application.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCategoryRequest {

    @NotBlank(message = "Tên thể loại trống")
    String name;

    @NotBlank(message = "Mô tả trống")
    String description;
}
