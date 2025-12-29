/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.repository;

import com.portal.interview.entity.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findCandidateByEmail(String email);
}
