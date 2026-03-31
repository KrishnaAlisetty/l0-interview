/**
 * @author Krishna Alisetty
 * @date 31-03-2026
 */

package com.portal.interview.controller;

import com.portal.interview.entity.Question;
import com.portal.interview.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long id) {
        List<Question> questions = questionService.fetchAllQuestions(id);

        return ResponseEntity.ok().body(questions);
    }
}
