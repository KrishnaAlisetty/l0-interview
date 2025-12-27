/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.repository;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.entity.InterviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InterviewStatusRepository extends JpaRepository<InterviewDetails, Long> {
    @Query(
            "select id from InterviewDetails id where id.interviewStatus = :status " +
                    "AND (id.candidate.email = :email OR id.interviewerEmail = :email)"
    )
    Optional<InterviewDetails> findInitiatedStatusByCandidateOrInterviewer(
            @Param("email") String email,
            @Param("statuses") InterviewStatus interviewStatus
    );
}
