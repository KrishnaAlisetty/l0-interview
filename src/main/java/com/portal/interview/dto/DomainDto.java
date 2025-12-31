/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.dto;

import com.portal.interview.constants.domain.business.BusinessDomain;
import com.portal.interview.constants.domain.tech.TechnicalDomain;

public record DomainDto<T>(
        T domain,
        Double score
) {
}
