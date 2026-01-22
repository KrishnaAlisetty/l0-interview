/**
 * @author Krishna Alisetty
 * @date 01-01-2026
 */

package com.portal.interview.util;

import com.portal.interview.components.SkillNormalizationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SkillNormalizer {

    private Map<String, String> normalizedValues = new HashMap<>();

    @Autowired
    public SkillNormalizer(SkillNormalizationProperties skillNormalizationProperties) {
        skillNormalizationProperties.getSkills().forEach(
                (canonical, synonyms) -> {
                    for (String syn : synonyms) {
                        normalizedValues.put(clean(syn), canonical);
                    }
                }
        );
    }

    public String normalize(String skill) {
        return normalizedValues.getOrDefault(clean(skill), skill);
    }

    public String clean(String value) {
        return value.toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .trim();
    }

}
