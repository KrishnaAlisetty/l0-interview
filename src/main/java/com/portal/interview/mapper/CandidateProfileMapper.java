/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.mapper;

import com.portal.interview.dto.CandidateProfile;
import com.portal.interview.entity.Candidate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CandidateProfileMapper {

    public Candidate toEntity(CandidateProfile candidateProfile) {
        Candidate candidate = new Candidate();
        candidate.setCreatedAt(candidateProfile.createdAt());
        candidate.setEmail(candidateProfile.email());
        candidate.setExperience(candidateProfile.experience());
        candidate.setMobile(candidateProfile.mobile());
        candidate.setName(candidateProfile.name());
        candidate.setRole(candidateProfile.role());
        candidate.setPrimarySkills(candidateProfile.primarySkills().stream().collect(Collectors.joining(",")));
        candidate.setSecondarySkills(candidateProfile.secondarySkills().stream().collect(Collectors.joining(",")));

        return candidate;
    }

    /*public CandidateProfile fromEntity(Candidate candidate) {
        CandidateProfile candidateProfile = new CandidateProfile(
                candidate.getName(),
                candidate.getEmail(),
                candidate.getExperience(),
                Arrays.asList(candidate.getPrimarySkills().split(",")),
                Arrays.asList(candidate.getSecondarySkills().split(",")),
                candidate.getMobile(),
                candidate.getRole(),
                candidate.getCreatedAt()
        );

        return candidateProfile;
    }*/
}
