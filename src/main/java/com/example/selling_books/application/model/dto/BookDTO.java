package com.example.selling_books.application.model.dto;

import com.example.selling_books.application.entity.BookImage;
import com.example.selling_books.application.entity.Category;
import com.example.selling_books.application.entity.OderItem;
import com.example.selling_books.application.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO extends BaseEnityDTO {

    Long id;
    String name;
    String author;
    String supplier;
    String publisher;
    String publicationYear;
    String description;
    String thumbnail;
    Float sellingPrice;
    Float cost;
    int stock;
    Float discount;
    Set<Category> categories;
    Set<BookImage> bookImages;
    String coverType;
    int pageCount;

    public String getCostString() {
        return String.format("%,.0f", this.getCost());
    }
    public String getDiscountString() {
        return String.format("%,.0f", this.getDiscount());
    }
    public String getSellingPriceString() {
        return String.format("%,.0f", this.getSellingPrice());
    }
    public String calculatePrice() {
        return String.format("%,.0f", this.getSellingPrice() - this.getSellingPrice()*this.getDiscount()/100);
    }
}
