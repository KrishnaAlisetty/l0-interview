/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.dto;

public record DomainDto<T>(
        T domain,
        Double score
) {
}
