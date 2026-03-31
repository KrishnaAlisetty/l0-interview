/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.repository;

import com.portal.interview.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {

    List<Question> findByCandidateId(Long id);
}
