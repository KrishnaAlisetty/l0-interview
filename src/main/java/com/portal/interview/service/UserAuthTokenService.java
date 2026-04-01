/**
 * @author Krishna Alisetty
 * @date 01-04-2026
 */

package com.portal.interview.service;

import com.portal.interview.entity.Candidate;
import com.portal.interview.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAuthTokenService {

    private CandidateRepository candidateRepository;

    public UserAuthTokenService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public String getInterviewLink(Candidate candidate) {

        String url = "http://localhost:5173/interview/"+candidate.getPassKey();
        return url;
    }
}
