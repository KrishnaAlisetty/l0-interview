/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.constants;

public enum Roles {

    CANDIDATE("candidate"),
    INTERVIEWER("interviewer");

    private String role;
    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
