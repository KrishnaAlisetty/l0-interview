/**
 * @author Krishna Alisetty
 * @date 17-01-2026
 */

package com.portal.interview.util;

import com.portal.interview.components.DomainDictionaryProperties;
import com.portal.interview.constants.domain.tech.TechnicalDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DomainCalculate {

    private static final double CORE_WEIGHT = 3.0;
    private static final double SUPPORTING_WEIGHT = 1.0;
    private static final double GENERIC_WEIGHT = 0.2;

    private DomainDictionaryProperties domainDictionaryProperties;

    @Autowired
    public DomainCalculate(DomainDictionaryProperties domainDictionaryProperties) {
        this.domainDictionaryProperties = domainDictionaryProperties;
    }

    public Map<TechnicalDomain, Double> scoreDomains(Set<String> normalizedSkills) {
        Map<TechnicalDomain, Double> map = new HashMap<>();
        Map<TechnicalDomain, DomainProfile> domains = domainDictionaryProperties.getDomains();
        assert domains.size() > 0;

        for (Map.Entry<TechnicalDomain, DomainProfile> e : domains.entrySet()) {
            double score = scoreDomain(e.getValue(), normalizedSkills);
            map.put(e.getKey(), score);
        }

        return map;
    }

    private double scoreDomain(DomainProfile profile, Set<String> normalizedSkills) {
        Double score = 0.0;
        int coreMatched = 0;

        for (String skill : normalizedSkills) {
            if (profile.core().contains(skill.toLowerCase())) {
                score += CORE_WEIGHT;
                coreMatched++;
            } else if (profile.supporting().contains(skill)) {
                score += SUPPORTING_WEIGHT;
            } else if (profile.generic().contains(skill)) {
                score += GENERIC_WEIGHT;
            }
        }

        if (coreMatched == 0) {
            return 0.0;
        }

        double maxScore = profile.core().size() * CORE_WEIGHT +
                profile.supporting().size() * SUPPORTING_WEIGHT ;

        return Math.round(score * 10.0) / maxScore;
    }
}
