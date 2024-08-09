package com.example.selling_books.application.config;

import com.example.selling_books.application.entity.Book;
import com.example.selling_books.application.entity.BookImage;
import com.example.selling_books.application.entity.Category;
import com.example.selling_books.application.model.request.CreateBookRequest;
import com.example.selling_books.application.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final CategoryRepository categoryRepository;


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
