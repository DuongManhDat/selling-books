package com.example.selling_books.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudinaryService {
    String uploadImage(MultipartFile file);
    List<String> uploadImage(List<MultipartFile> files);
}
