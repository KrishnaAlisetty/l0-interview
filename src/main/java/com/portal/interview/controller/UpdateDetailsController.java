package com.portal.interview.controller;

import com.portal.interview.dto.CandidateProfile;
import com.portal.interview.dto.UploadDetails;
import com.portal.interview.entity.Candidate;
import com.portal.interview.service.DomainResolverService;
import com.portal.interview.service.ExtractInfoService;
import com.portal.interview.service.ResumeParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateDetailsController {

    private ResumeParsingService resumeParsingService;
    private ExtractInfoService extractInfoService;
    private DomainResolverService domainResolverService;

    @Autowired
    public UpdateDetailsController(ResumeParsingService resumeParsingService, ExtractInfoService extractInfoService, DomainResolverService domainResolverService) {
        this.resumeParsingService = resumeParsingService;
        this.extractInfoService = extractInfoService;
        this.domainResolverService = domainResolverService;
    }

    @PostMapping(value = "/details", consumes = "multipart/form-data")
    public String handleFileUpload(UploadDetails uploadDetails) {
        String resumeText = resumeParsingService.parseResume(
            uploadDetails.file()
        );

        Candidate candidate = extractInfoService.extractResumeInfo(resumeText);
        domainResolverService.resolveAndUpdateDomain(candidate);
        return "{\"status\": \"success\", \"message\": \"File uploaded successfully\"}";
    }
}
