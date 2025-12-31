/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.service;

import com.portal.interview.constants.domain.tech.TechnicalDomain;
import com.portal.interview.dto.CandidateProfile;
import com.portal.interview.dto.DomainDto;
import com.portal.interview.entity.Candidate;
import com.portal.interview.mapper.CandidateProfileMapper;
import com.portal.interview.repository.CandidateRepository;
import com.portal.interview.util.DomainUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DomainResolverService {

    private CandidateRepository candidateRepository;

    public DomainResolverService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void resolveAndUpdateDomain(Candidate candidate) {
        //Candidate candidate = new CandidateProfileMapper().toEntity(candidateProfile);
        List<String> primarySkills = Arrays.asList(candidate.getPrimarySkills().split(","));
        List<String> secondarySkills = Arrays.asList(candidate.getSecondarySkills().split(","));
        List<String> skills = new ArrayList<>(primarySkills);
        skills.addAll(secondarySkills);
        Map<TechnicalDomain, Double> primaryDomain = DomainUtil.resolveDomainAndScore(skills.stream().map(String::toLowerCase).collect(Collectors.toList()));

        DomainDto<TechnicalDomain> domain = DomainUtil.predictDomain(primaryDomain);

        candidate.setTechnicalDomainScore(Map.of(domain.domain(), domain.score()));
        candidateRepository.save(candidate);

    }
}
