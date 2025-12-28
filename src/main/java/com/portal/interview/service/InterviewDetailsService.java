/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.service;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.entity.InterviewDetails;
import com.portal.interview.repository.InterviewStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewDetailsService {

    private final InterviewStatusRepository interviewStatusRepository;

    public InterviewDetailsService(InterviewStatusRepository interviewStatusRepository) {
        this.interviewStatusRepository = interviewStatusRepository;
    }

    public List<InterviewDetails> interviewProcessByInitiatedOrOngoing(String email) {
        return interviewStatusRepository.findInitiatedStatusByCandidateOrInterviewer(email, List.of(InterviewStatus.INITIATED, InterviewStatus.ON_GOING));
    }

    public void create(InterviewDetails interviewDetails){
        interviewStatusRepository.save(interviewDetails);
    }

    public void updateOnInterviewerJoin(InterviewDetails interviewDetails) {
        interviewStatusRepository.onInterviewerJoin(interviewDetails.getStartedAt(), interviewDetails.getInterviewerEmail(), InterviewStatus.ON_GOING, interviewDetails.getJwt(), interviewDetails.getInterviewStatus());
    }
}
