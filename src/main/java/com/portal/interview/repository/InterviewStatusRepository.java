/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.repository;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.entity.InterviewDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InterviewStatusRepository extends JpaRepository<InterviewDetails, Long> {
    @Query(
            "select id from InterviewDetails id where id.interviewStatus in :statuses " +
                    "AND (id.candidate.email = :email OR id.interviewerEmail = :email)"
    )
    List<InterviewDetails> findInitiatedStatusByCandidateOrInterviewer(
            @Param("email") String email,
            @Param("statuses") List<InterviewStatus> interviewStatus
    );

    @Modifying
    @Transactional
    @Query("""
    UPDATE InterviewDetails ip
    SET ip.interviewStatus = :status,
        ip.startedAt = :startedAt,
        ip.interviewerEmail = :interviewerEmail
    WHERE ip.jwt = :jwt
      AND ip.interviewStatus = :expectedStatus
""")
    int onInterviewerJoin(
            @Param("startedAt") LocalDateTime startedAt,
            @Param("interviewerEmail") String interviewerEmail,
            @Param("status") InterviewStatus updateStatus,
            @Param("jwt") String jwt,
            @Param("expectedStatus") InterviewStatus expectedStatus
    );
}
