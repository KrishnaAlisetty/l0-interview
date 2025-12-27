/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.constants;

public enum InterviewStatus {
    INITIATED("initiated"),
    ON_GOING("ongoing"),
    COMPLETED("completed");

    private String value;

    InterviewStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
