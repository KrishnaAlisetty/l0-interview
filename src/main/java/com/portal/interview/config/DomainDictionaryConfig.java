/**
 * @author Krishna Alisetty
 * @date 01-01-2026
 */

package com.portal.interview.config;

import com.portal.interview.components.DomainDictionaryProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DomainDictionaryProperties.class)
public class DomainDictionaryConfig {
}
