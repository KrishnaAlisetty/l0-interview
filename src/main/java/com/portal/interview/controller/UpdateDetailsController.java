package com.portal.interview.controller;

import com.portal.interview.constants.domain.tech.TechnicalDomain;
import com.portal.interview.dto.UploadDetails;
import com.portal.interview.entity.Candidate;
import com.portal.interview.service.CandidateService;
import com.portal.interview.service.DomainScoringService;
import com.portal.interview.service.ExtractInfoService;
import com.portal.interview.service.ResumeParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/update")
public class UpdateDetailsController {

    private ResumeParsingService resumeParsingService;
    private ExtractInfoService extractInfoService;
    private DomainScoringService domainScoringService;
    private CandidateService candidateService;

    @Autowired
    public UpdateDetailsController(ResumeParsingService resumeParsingService, ExtractInfoService extractInfoService, DomainScoringService domainScoringService, CandidateService candidateService) {
        this.resumeParsingService = resumeParsingService;
        this.extractInfoService = extractInfoService;
        this.domainScoringService = domainScoringService;
        this.candidateService = candidateService;
    }

    @PostMapping(value = "/details", consumes = "multipart/form-data")
    public String handleFileUpload(UploadDetails uploadDetails) {
        String resumeText = resumeParsingService.parseResume(
            uploadDetails.file()
        );

        Candidate candidate = extractInfoService.extractResumeInfo(resumeText);
        List<String> primarySkills = Arrays.asList(candidate.getPrimarySkills().split(","));
        List<String> secondarySkills = Arrays.asList(candidate.getSecondarySkills().split(","));
        List<String> skills = new ArrayList<>(primarySkills);
        skills.addAll(secondarySkills);

        Map<TechnicalDomain, Double> domain =  domainScoringService.calculateAndUpdateTechDomainScore(skills);
        candidate.setTechnicalDomainScore(domain);

        candidateService.saveCandidate(candidate);

        return "{\"status\": \"success\", \"message\": \"File uploaded successfully\"}";
    }
}
