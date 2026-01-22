/**
 * @author Krishna Alisetty
 * @date 01-01-2026
 */

package com.portal.interview.components;

import com.portal.interview.constants.domain.tech.TechnicalDomain;
import com.portal.interview.util.DomainProfile;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties
public class DomainDictionaryProperties {
    private Map<TechnicalDomain, DomainProfile> domains = new HashMap<>();

    public Map<TechnicalDomain, DomainProfile> getDomains() {
        return domains;
    }

    public void setDomains(Map<TechnicalDomain, DomainProfile> domains) {
        this.domains = domains;
    }
}
