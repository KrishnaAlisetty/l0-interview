/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.entity;

import com.portal.interview.constants.domain.business.BusinessDomain;
import com.portal.interview.constants.domain.tech.TechnicalDomain;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @ElementCollection
    @CollectionTable(
            name = "tech_domain_score",
            joinColumns = @JoinColumn(name = "candidate_id")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "domain")
    @Column(name = "score")
    private Map<TechnicalDomain, Double> technicalDomainScore;

    @ElementCollection(targetClass = BusinessDomain.class)
    @CollectionTable(
            name = "candidate_business_domains",
            joinColumns = @JoinColumn(name = "candidate_id")
    )
    @Column(name = "business_domain")
    @Enumerated(EnumType.STRING)
    private List<BusinessDomain> businessDomains;


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

    public Map<TechnicalDomain, Double> getTechnicalDomainScore() {
        return technicalDomainScore;
    }

    public void setTechnicalDomainScore(Map<TechnicalDomain, Double> technicalDomainScore) {
        this.technicalDomainScore = technicalDomainScore;
    }

    public List<BusinessDomain> getBusinessDomains() {
        return businessDomains;
    }

    public void setBusinessDomains(List<BusinessDomain> businessDomains) {
        this.businessDomains = businessDomains;
    }
}
