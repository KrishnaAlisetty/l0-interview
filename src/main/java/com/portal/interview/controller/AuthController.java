/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.controller;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.constants.Roles;
import com.portal.interview.dto.LoginRequest;
import com.portal.interview.entity.Candidate;
import com.portal.interview.entity.InterviewDetails;
import com.portal.interview.projetions.CandidateAuthView;
import com.portal.interview.repository.CandidateRepository;
import com.portal.interview.service.InterviewDetailsService;
import com.portal.interview.service.JwtService;
import com.portal.interview.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
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
        String interviewerEmail = loginRequest.interviewerEmail();
        String role = loginRequest.role();
        Candidate candidate;

        boolean roleMatch = Arrays.stream(Roles.values()).anyMatch(c -> c.getRole().equalsIgnoreCase(role));
        if (!roleMatch) {
            throw new IllegalStateException("Role doesn't match");
        }

        Optional<Candidate> optionalCandidate = candidateRepository.findCandidateByEmail(email);

        candidate = optionalCandidate.get();


        List<InterviewDetails> interviewProcesses = interviewDetailsService.interviewProcessByInitiatedOrOngoing(email);

        boolean isInterviewOngoing = interviewProcesses.stream().anyMatch(v -> v.getInterviewStatus().equals(InterviewStatus.ON_GOING));
        InterviewDetails interviewDetails = interviewProcesses.stream().filter(v -> v.getInterviewStatus().equals(InterviewStatus.INITIATED)).findFirst().orElse(null);
        if (isInterviewOngoing) {
            throw new IllegalStateException("Interview is happen with candidate who is associated with provided email id");
        }
        if (interviewDetails != null) {
            interviewDetails.setStartedAt(LocalDateTime.now());
            interviewDetails.setInterviewerEmail(interviewerEmail);
            interviewDetailsService.updateOnInterviewerJoin(interviewDetails);
            return ResponseEntity.ok(Map.of("token", interviewDetails.getJwt(), "roomId", interviewDetails.getRoomId()));
        }

        String roomId = roomService.createRoomId();
        String token = jwtService.generateToken(candidate.getId().toString(), email, role, roomId);

        interviewDetails = new InterviewDetails();
        interviewDetails.setCandidate(candidate);
        interviewDetails.setJwt(token);
        interviewDetails.setRoomId(roomId);
        if (role.equalsIgnoreCase(Roles.CANDIDATE.getRole())) {
            interviewDetails.setCandidate(candidate);
            interviewDetails.setInitiatedBy(Roles.CANDIDATE);
        } else {
            interviewDetails.setInterviewerEmail(email);
            interviewDetails.setInitiatedBy(Roles.INTERVIEWER);
        }
        interviewDetails.setInterviewStatus(InterviewStatus.INITIATED);
        interviewDetails.setCreatedAt(LocalDateTime.now());

        interviewDetailsService.create(interviewDetails);

        model.addAttribute("room_id", roomId);
        return ResponseEntity.ok(Map.of("token", token, "roomId", roomId));
    }


    @GetMapping("/{token}")
    public ResponseEntity<?> authenticateUser(@PathVariable("token") String token) {
        Optional<CandidateAuthView> candidate = candidateRepository.findCandidateByPassKey(token);

        if (candidate.isPresent()) {
            return ResponseEntity.ok().body(candidate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }
    }
}
