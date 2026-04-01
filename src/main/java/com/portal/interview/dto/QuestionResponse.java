/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.dto;

import java.util.List;

public record QuestionResponse(
        List<Question> questions
) {
}
