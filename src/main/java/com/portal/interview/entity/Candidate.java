/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.entity;

import com.portal.interview.constants.domain.business.BusinessDomain;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CANDIDATE")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Float experience;
    @Column(name ="primary_skills", length = 1000)
    private String primarySkills;
    @Column(name ="secondary_skills", length = 1000)
    private String secondarySkills;
    private String mobile;
    private String role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "pass_key")
    private String passKey;

    @OneToMany(mappedBy = "candidate")
    private Set<Question> questions;

    @OneToMany(mappedBy = "candidate")
    private Set<BusinessRequirementEntity> businessRequirementEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getExperience() {
        return experience;
    }

    public void setExperience(Float experience) {
        this.experience = Float.valueOf(experience);
    }

    public String getPrimarySkills() {
        return primarySkills;
    }

    public void setPrimarySkills(String primarySkills) {
        this.primarySkills = primarySkills;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(String secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    @PrePersist
    public void generateLoginId(){
        this.passKey = UUID.randomUUID().toString();
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<BusinessRequirementEntity> getBusinessRequirementEntities() {
        return businessRequirementEntities;
    }

    public void setBusinessRequirementEntities(Set<BusinessRequirementEntity> businessRequirementEntities) {
        this.businessRequirementEntities = businessRequirementEntities;
    }
}
