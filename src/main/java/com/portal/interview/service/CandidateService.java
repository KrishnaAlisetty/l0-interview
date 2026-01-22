/**
 * @author Krishna Alisetty
 * @date 19-01-2026
 */

package com.portal.interview.service;

import com.portal.interview.entity.Candidate;
import com.portal.interview.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void saveCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }
}
