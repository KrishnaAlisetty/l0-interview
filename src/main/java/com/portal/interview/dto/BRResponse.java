/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.dto;

import java.util.List;
import java.util.Set;

public record BRResponse(
        Integer matchPercentage,
        Set<String> strengths,
        List<String> gaps,
        String summary
) {
}
