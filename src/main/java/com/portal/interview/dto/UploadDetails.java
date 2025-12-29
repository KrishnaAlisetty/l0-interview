package com.portal.interview.dto;

import com.portal.interview.dto.SchedulerInfo;
import org.springframework.web.multipart.MultipartFile;

public record UploadDetails(
    String candidateName,
    String candidateEmail,
    String interviewerName,
    String interviewerEmail,
    String scheduleTime,
    String timeZone,
    MultipartFile file
) {
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB

    public UploadDetails {
        if (candidateName == null) {
            throw new IllegalArgumentException(
                "Candidate name cannot be empty"
            );
        }
        if (candidateEmail == null) {
            throw new IllegalArgumentException(
                "Candidate email cannot be empty"
            );
        }
        if (interviewerName == null) {
            throw new IllegalArgumentException(
                "Interviewer name cannot be empty"
            );
        }
        if (interviewerEmail == null) {
            throw new IllegalArgumentException(
                "Interviewer email cannot be empty"
            );
        }

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                "File size exceeds maximum limit"
            );
        }
    }
}
