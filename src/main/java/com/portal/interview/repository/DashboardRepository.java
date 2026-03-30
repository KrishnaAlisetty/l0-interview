/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.repository;

import com.portal.interview.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Candidate, Long> {

    @Query(
            " SELECT DISTINCT c FROM Candidate c\n" +
                    "    LEFT JOIN FETCH c.businessRequirementEntities br"
    )
    List<Candidate> findAll();

}
