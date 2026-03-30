package com.portal.interview.controller;

import com.portal.interview.dto.Response;
import com.portal.interview.dto.UploadDetails;
import com.portal.interview.entity.Candidate;
import com.portal.interview.service.CandidateService;
import com.portal.interview.service.DomainScoringService;
import com.portal.interview.service.ExtractInfoService;
import com.portal.interview.service.ResumeParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<Response> handleFileUpload(UploadDetails uploadDetails) {
        String resumeText = resumeParsingService.parseResume(
            uploadDetails.file()
        );

        Candidate candidate = extractInfoService.extractResumeInfo(resumeText);

        Candidate candidate1 = candidateService.saveCandidate(candidate);

        extractInfoService.prepareQuestionsForCandidate(candidate.getExperience(), candidate.getPrimarySkills(), candidate.getSecondarySkills(), candidate1.getId());

        if(uploadDetails.brNumber() != null && !uploadDetails.brNumber().equals("")) {
            extractInfoService.saveAndCalculateBRMatch(candidate.getExperience(), candidate.getPrimarySkills(), candidate.getSecondarySkills(), candidate1.getId(), uploadDetails.brNumber());
        }
        return new ResponseEntity<>(
                new Response("success", "File submitted successfully, Data can be found on dashboard after some time", candidate1.getId()),
                HttpStatus.OK
        );
    }
}
