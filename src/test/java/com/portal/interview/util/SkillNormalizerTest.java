/**
 * @author Krishna Alisetty
 * @date 01-01-2026
 */

package com.portal.interview.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(
        properties = "spring.config.import=classpath:skill-normalization.yml"
)
public class SkillNormalizerTest {

    @Autowired
    private SkillNormalizer skillNormalizer;

    @Test
    public void normalizeTest() {
        String skill = "Spring boot";
        String normalizedSkill = skillNormalizer.normalize(skill);

        assertEquals("springboot", normalizedSkill);
    }

}