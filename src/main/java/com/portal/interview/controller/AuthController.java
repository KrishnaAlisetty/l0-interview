/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.controller;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.constants.Roles;
import com.portal.interview.entity.InterviewDetails;
import com.portal.interview.service.InterviewDetailsService;
import com.portal.interview.service.JwtService;
import com.portal.interview.dto.LoginRequest;
import com.portal.interview.entity.Candidate;
import com.portal.interview.repository.CandidateRepository;
import com.portal.interview.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    InterviewDetailsService interviewDetailsService;
    @Autowired
    JwtService jwtService;

    @Autowired
    RoomService roomService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, Model model) {
        String email = loginRequest.email();
        String role = loginRequest.role();
        Candidate candidate;

        boolean roleMatch = Arrays.stream(Roles.values()).anyMatch(c -> c.getRole().equalsIgnoreCase(role));
        if(!roleMatch) {
            throw new IllegalStateException("Role doesn't match");
        }

        Optional<Candidate> optionalCandidate = candidateRepository.findCandidateByEmail(email);

        candidate = optionalCandidate.get();


        Optional<InterviewDetails> interviewProcess = interviewDetailsService.interviewProcessByInitiated(email);

        if (interviewProcess.isPresent()) {
            InterviewDetails  interviewDetails = interviewProcess.get();

            interviewDetailsService.update(interviewDetails);
            return ResponseEntity.ok(Map.of("token", interviewProcess.get().getJwt()));
        }

        String roomId = roomService.createRoomId();
        String token  = jwtService.generateToken(candidate.getId().toString(), email, role, roomId);

        InterviewDetails interviewDetails = new InterviewDetails();
        interviewDetails.setCandidate(candidate);
        if (role.equals(Roles.CANDIDATE.getRole())) {
            interviewDetails.setCandidate(candidate);
            interviewDetails.setInitiatedBy(Roles.CANDIDATE);
        } else {
            interviewDetails.setInterviewerEmail(email);
            interviewDetails.setInitiatedBy(Roles.INTERVIEWER);
        }
        interviewDetails.setInterviewStatus(InterviewStatus.INITIATED);
        interviewDetails.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
