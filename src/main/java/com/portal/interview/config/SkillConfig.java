/**
 * @author Krishna Alisetty
 * @date 01-01-2026
 */

package com.portal.interview.config;

import com.portal.interview.components.SkillNormalizationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SkillNormalizationProperties.class)
public class SkillConfig {
}
