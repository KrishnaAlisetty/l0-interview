/**
 * @author Krishna Alisetty
 * @date 01-04-2026
 */

package com.portal.interview.projetions;

import java.util.Set;

public interface CandidateAuthView {
    interface QuestionView {
        String getQuestion();
    }
    Long getId();
    Set<QuestionView> getQuestions();
    String getName();
}
