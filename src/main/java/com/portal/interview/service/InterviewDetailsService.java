/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.service;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.entity.InterviewDetails;
import com.portal.interview.repository.InterviewStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewDetailsService {

    private final InterviewStatusRepository interviewStatusRepository;

    public InterviewDetailsService(InterviewStatusRepository interviewStatusRepository) {
        this.interviewStatusRepository = interviewStatusRepository;
    }

    public Optional<InterviewDetails> interviewProcessByInitiated(String email) {
        return interviewStatusRepository.findInitiatedStatusByCandidateOrInterviewer(email, InterviewStatus.INITIATED);
    }

    public void create(InterviewDetails interviewDetails){
        interviewStatusRepository.save(interviewDetails);
    }

    public void update(InterviewDetails interviewDetails) {

    }
}
