/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.entity;

import com.portal.interview.constants.domain.business.BusinessDomain;
import com.portal.interview.constants.domain.tech.TechnicalDomain;
import jakarta.persistence.*;

@Entity
@Table(name = "candidate_domain")
public class CandidateDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tech_domain")
    private TechnicalDomain techDomain;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_domain")
    private BusinessDomain businessDomain;
    @Column(name = "tech_domain_score")
    private double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TechnicalDomain getTechDomain() {
        return techDomain;
    }

    public void setTechDomain(TechnicalDomain techDomain) {
        this.techDomain = techDomain;
    }

    public BusinessDomain getBusinessDomain() {
        return businessDomain;
    }

    public void setBusinessDomain(BusinessDomain businessDomain) {
        this.businessDomain = businessDomain;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
