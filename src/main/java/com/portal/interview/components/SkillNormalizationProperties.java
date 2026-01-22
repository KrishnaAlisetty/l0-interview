/**
 * @author Krishna Alisetty
 * @date 01-01-2026
 */

package com.portal.interview.components;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties
public class SkillNormalizationProperties {
    private Map<String, List<String>> skills = new HashMap<>();

    public Map<String, List<String>> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, List<String>> mappings) {
        this.skills = mappings;
    }
}
