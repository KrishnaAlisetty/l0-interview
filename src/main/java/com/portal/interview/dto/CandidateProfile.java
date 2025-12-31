/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.portal.interview.mapper.ExperienceDeserializer;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CandidateProfile(
        String name,
        String email,
        @JsonDeserialize(using = ExperienceDeserializer.class)
        Float experience,
        List<String> primarySkills,
        List<String> secondarySkills,
        String mobile,
        String domain,
        String role,
        LocalDateTime createdAt
) {
    public CandidateProfile {
        createdAt = LocalDateTime.now();
    }
}
