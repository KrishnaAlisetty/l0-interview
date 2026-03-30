/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.repository;

import com.portal.interview.entity.BusinessRequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRequirementRepository extends JpaRepository<BusinessRequirementEntity, Long> {
}
