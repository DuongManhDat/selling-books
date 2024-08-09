package com.example.selling_books.application.model.request;

import com.example.selling_books.application.entity.BookImage;
import com.example.selling_books.application.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateBookRequest {
    @NotBlank(message = "Tên sách trống")
    String name;

    @NotBlank(message = "Tên tác giả trống")
    String author;

    @NotBlank(message = "Tên nhà xuất bản trống")
    String publisher;

    @NotBlank(message = "Năm xuất bản trống")
    String publicationYear;

    @NotBlank(message = "Mô tả trống")
    String description;

    @NotBlank(message = "Hình ảnh trống")
    String thumbnail;

    @NotBlank(message = "Giá bán trống")
    Float sellingPrice;

    @NotBlank(message = "Giá vốn trống")
    Float cost;

    @NotBlank(message = "Thể loại trống")
    List<String> categories;

    List<String> bookImages;
}
