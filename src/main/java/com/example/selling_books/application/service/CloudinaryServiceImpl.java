package com.example.selling_books.application.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.selling_books.application.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService{

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            if (file != null) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                return uploadResult.get("url").toString();
            }

            return null;
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<String> uploadImage(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                if (file != null && !file.isEmpty()) {
                    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                    urls.add(uploadResult.get("url").toString());
                }
            } catch (IOException e) {
                throw new BadRequestException(e.getMessage());
            }
        }

        return urls;
    }
}
