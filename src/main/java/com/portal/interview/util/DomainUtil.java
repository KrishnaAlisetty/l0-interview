/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.util;

import com.portal.interview.constants.domain.tech.TechnicalDomain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.portal.interview.constants.domain.tech.TechnicalDomainSkills;
import com.portal.interview.dto.DomainDto;

public class DomainUtil {

    public static Map<TechnicalDomain, Double> resolveDomainAndScore(final List<String> techSkills) {
        Map<TechnicalDomain, Double> domainScoreMap = new HashMap<>();

        List<String> normalizedSkills = techSkills.stream().map(LanguageSynonyms::normalizeSkill).toList();
        TechnicalDomainSkills.getSkills().forEach((domain, skills) -> {
            long matchedSkills = skills.stream().map(String::toLowerCase).filter(normalizedSkills::contains).count();

            double score = Math.round((double) matchedSkills / skills.size() * 10.0) / 10.0;
            domainScoreMap.put(domain, score);
        });

        return domainScoreMap;
    }

    public static <T> DomainDto<T> predictDomain(final Map<TechnicalDomain, Double> domainAndScore) {
        List<Map.Entry<TechnicalDomain, Double>> ranked = domainAndScore.entrySet().stream().sorted(
                Comparator.comparingDouble(Map.Entry<TechnicalDomain, Double>::getValue).reversed()
        ).toList();

        TechnicalDomain primary = ranked.get(0).getKey();
        Double score = ranked.get(0).getValue();

        if(primary.equals(TechnicalDomain.FRONTEND)) {
            Map.Entry<TechnicalDomain, Double> entry = ranked.stream().filter(me -> me.getKey().equals(TechnicalDomain.JAVA_BACKEND)).findFirst().orElse(null);
            if(entry != null) {
                double javaScore = entry.getValue();
                primary = javaScore >= 0.5 && score >= 0.5 ? TechnicalDomain.JAVA_FULLSTACK : primary;
                score = (score + javaScore) / 2;
            }
        } else {
            Map.Entry<TechnicalDomain, Double> entry = ranked.stream().filter(me -> me.getKey().equals(TechnicalDomain.FRONTEND)).findFirst().orElse(null);
            if(entry != null && TechnicalDomain.JAVA_BACKEND.equals(primary)) {
                double uiScore = Math.round(entry.getValue());
                primary = uiScore >= 0.5 && score >= 0.5 ? TechnicalDomain.JAVA_FULLSTACK : primary;
                score = (score + uiScore) / 2;
            }
        }

        return new DomainDto(primary, score);
    }
}
