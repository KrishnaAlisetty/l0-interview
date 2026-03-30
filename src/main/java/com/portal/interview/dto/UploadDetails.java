package com.portal.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public record UploadDetails(
    MultipartFile file,
    String brNumber
) {
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB

    public UploadDetails {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                "File size exceeds maximum limit"
            );
        }
    }
}
