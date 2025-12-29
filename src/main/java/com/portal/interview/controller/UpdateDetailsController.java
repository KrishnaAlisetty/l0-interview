package com.portal.interview.controller;

import com.portal.interview.dto.UploadDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/update")
public class UpdateDetailsController {

    @PostMapping(value = "/interviewinfo", consumes = "multipart/form-data")
    public String handleFileUpload(UploadDetails uploadDetails) {
        // Handle file upload logic here
        System.out.println(uploadDetails.candidateName());
        return "{\"status\": \"success\", \"message\": \"File uploaded successfully\"}";
    }
}
