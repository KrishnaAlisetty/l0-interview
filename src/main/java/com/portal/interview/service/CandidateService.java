/**
 * @author Krishna Alisetty
 * @date 19-01-2026
 */

package com.portal.interview.service;

import com.portal.interview.constants.CandidateStatus;
import com.portal.interview.dto.StatusUpdateRequest;
import com.portal.interview.entity.Candidate;
import com.portal.interview.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Component
public class CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate updateStatus(Long id, StatusUpdateRequest status) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow();

        Optional<CandidateStatus> candidateStatus = Arrays.stream(CandidateStatus.values()).filter(v -> v.name().equalsIgnoreCase(status.status())).findFirst();

        if (candidateStatus.isPresent()) {
            candidate.setInterviewStatus(candidateStatus.get().name());
            if (candidateStatus.get().name().equalsIgnoreCase(CandidateStatus.SCHEDULE.name()) && status.date() != null && !status.date().equalsIgnoreCase("")) {
                candidate.setScheduledDate(LocalDateTime.parse(status.date()));
            }
        }

        return candidate;
    }
}
