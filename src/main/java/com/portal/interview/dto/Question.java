/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.dto;

public record Question(
        String question,
        String skill,
        String difficulty,
        String category
) {
}
