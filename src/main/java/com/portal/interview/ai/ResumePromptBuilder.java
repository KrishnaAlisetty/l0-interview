/**
 * @author Krishna Alisetty
 * @date 29-12-2025
 */

package com.portal.interview.ai;

import com.portal.interview.constants.ResumePromptTemplate;
import com.portal.interview.constants.ResumeSchemaConstants;

public class ResumePromptBuilder {
    private String resumeText;

    public ResumePromptBuilder withResumeText(String resumeText) {
        this.resumeText = resumeText;
        return this;
    }

    public String buildSystemPrompt() {
        return ResumePromptTemplate.SYSTEM_PROMPT;
    }

    public String buildUserPrompt() {
        return ResumePromptTemplate.USER_PROMPT.formatted(
                ResumeSchemaConstants.CANDIDATE_SCHEMA,
                resumeText
        );
    }

    public String buildQuestionGenerationPrompt(Float experience, String primary_skills, String secondary_skills, int count) {
        return ResumePromptTemplate.GENERATE_QUESTION_PROMPT.formatted(experience, primary_skills, secondary_skills, count);
    }

    public String buildBRMatchWithSkillSet(Float experience, String primary_skills, String secondary_skills, String job_description) {
        return ResumePromptTemplate.BR_MATCH_PROMPT
                .replace("{primary_skills}", primary_skills)
                .replace("{secondary_skills}", secondary_skills)
                .replace("{experience}", String.valueOf(experience))
                .replace("{job_description}", job_description);
    }
}
