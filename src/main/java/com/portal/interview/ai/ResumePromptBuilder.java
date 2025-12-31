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
}
