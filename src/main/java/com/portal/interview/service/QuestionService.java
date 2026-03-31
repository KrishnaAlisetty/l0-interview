/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.service;

import com.portal.interview.entity.Question;
import com.portal.interview.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionsRepository questionsRepository;

    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Transactional
    public List<Question> fetchAllQuestions(Long id) {
        return questionsRepository.findByCandidateId(id);
    }
}
