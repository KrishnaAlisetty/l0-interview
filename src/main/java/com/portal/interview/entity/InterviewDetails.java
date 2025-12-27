/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.entity;

import com.portal.interview.constants.InterviewStatus;
import com.portal.interview.constants.Roles;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Entity
@Table(name = "INTERVIEW_DETAILS")
public class InterviewDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jwt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "interview_status")
    private InterviewStatus interviewStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "initiated_by", nullable = false)
    private Roles initiatedBy;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Candidate candidate;
    private String interviewerEmail;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public Roles getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(Roles initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public Candidate getCandidateEmail() {
        return candidate;
    }

    public void setCandidateEmail(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }
}
