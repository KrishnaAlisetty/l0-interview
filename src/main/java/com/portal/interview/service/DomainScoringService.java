/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.service;

import com.portal.interview.constants.domain.tech.TechnicalDomain;
import com.portal.interview.repository.CandidateRepository;
import com.portal.interview.util.DomainCalculate;
import com.portal.interview.util.SkillNormalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DomainScoringService {

    private SkillNormalizer skillNormalizer;
    private DomainCalculate domainCalculate;

    @Autowired
    public DomainScoringService(SkillNormalizer skillNormalizer, DomainCalculate domainCalculate) {
        this.skillNormalizer = skillNormalizer;
        this.domainCalculate = domainCalculate;
    }

    public Map<TechnicalDomain, Double> calculateAndUpdateTechDomainScore(List<String> techSkills) {

        Set<String> normalizedSkills = techSkills.stream().map(skillNormalizer::normalize).collect(Collectors.toSet());

        Map<TechnicalDomain, Double> scores = domainCalculate.scoreDomains(normalizedSkills);

        return resolveDomain(scores);

    }

    private static Map<TechnicalDomain, Double> resolveDomain(Map<TechnicalDomain, Double> scores) {
        Map<TechnicalDomain, Double> domain = new HashMap<>();

        List<Map.Entry<TechnicalDomain, Double>> domains = scores.entrySet()
                .stream()
                .sorted(
                        Comparator.comparingDouble(
                                Map.Entry<TechnicalDomain, Double>::getValue
                        ).reversed()
                ).collect(Collectors.toList());


        var backEnd = domains.stream().filter(v -> v.getKey() == TechnicalDomain.BACKEND && v.getValue() > 0.4).findFirst();
        var frontEnd = domains.stream().filter(v -> v.getKey() == TechnicalDomain.FRONTEND && v.getValue() > 0.4).findFirst();

        if (backEnd.isPresent() && frontEnd.isPresent()) {
            domain.put(TechnicalDomain.FULLSTACK, (backEnd.get().getValue() + frontEnd.get().getValue()) / 2);
        } else {
            domain.put(domains.get(0).getKey(), domains.get(0).getValue());
        }
        return domain;
    }


}
