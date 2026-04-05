package com.portal.interview.controller;

import com.portal.interview.constants.CandidateStatus;
import com.portal.interview.dto.Response;
import com.portal.interview.dto.StatusUpdateRequest;
import com.portal.interview.dto.UploadDetails;
import com.portal.interview.entity.Candidate;
import com.portal.interview.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
@CrossOrigin(origins = "http://localhost:5173")
public class CandidateDetailsUpdateController {

    private ResumeParsingService resumeParsingService;
    private ExtractInfoService extractInfoService;
    private CandidateService candidateService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @Autowired
    public CandidateDetailsUpdateController(ResumeParsingService resumeParsingService, ExtractInfoService extractInfoService, CandidateService candidateService) {
        this.resumeParsingService = resumeParsingService;
        this.extractInfoService = extractInfoService;
        this.candidateService = candidateService;
    }


    @PostMapping(value = "/details", consumes = "multipart/form-data")
    public ResponseEntity<Response> handleFileUpload(UploadDetails uploadDetails) {
        String resumeText = resumeParsingService.parseResume(
                uploadDetails.file()
        );

        Candidate candidate = extractInfoService.extractResumeInfo(resumeText);

        candidate.setInterviewStatus(CandidateStatus.NEW.name());
        Candidate candidate1 = candidateService.saveCandidate(candidate);

        extractInfoService.prepareQuestionsForCandidate(candidate.getExperience(), candidate.getPrimarySkills(), candidate.getSecondarySkills(), candidate1.getId());

        if (uploadDetails.brNumber() != null && !uploadDetails.brNumber().equals("")) {
            System.out.println(candidate1.getRole());
            extractInfoService.saveAndCalculateBRMatch(candidate.getExperience(), candidate.getPrimarySkills(), candidate.getSecondarySkills(), candidate1.getId(), uploadDetails.brNumber());
        }
        return new ResponseEntity<>(
                new Response("success", "File submitted successfully, Data can be found on dashboard after some time", candidate1.getId()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request
    ) {
        Candidate candidate = candidateService.updateStatus(id, request);
        if (request.status().equalsIgnoreCase(CandidateStatus.SCHEDULE.name())) {
            String url = userAuthTokenService.getInterviewLink(candidate);
            String body = " Hello" + candidate.getName() + ",\n" +
                    "\n" +
                    "            You have been invited for an interview on " + request.date() + ".\n" +
                    "\n" +
                    "            Click the link below to start:\n" +
                    "            " + url + "\n" +
                    "\n" +
                    "            Regards,\n" +
                    "            Interview Team";

            mailService.sendSimpleMail("krishnaalisetty@gmail.com", "Interview Invitation", body);
        }
        return ResponseEntity.ok().build();
    }
}
