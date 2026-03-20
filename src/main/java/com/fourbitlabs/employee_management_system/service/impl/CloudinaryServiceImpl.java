package com.fourbitlabs.employee_management_system.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fourbitlabs.employee_management_system.service.interfaces.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Cannot upload an empty file");
        }
        
        String contentType = file.getContentType();
        if (contentType == null || !(contentType.equals("application/pdf") || 
            contentType.equals("application/msword") || 
            contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
            throw new RuntimeException("Only PDF and DOC/DOCX files are allowed");
        }

        try {
            return cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto")
            );
        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
}
