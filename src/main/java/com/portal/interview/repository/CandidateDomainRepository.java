/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.repository;

import com.portal.interview.entity.CandidateDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDomainRepository extends JpaRepository<CandidateDomain, Long> {
}
