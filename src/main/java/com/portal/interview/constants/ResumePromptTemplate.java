/**
 * @author Krishna Alisetty
 * @date 29-12-2025
 */

package com.portal.interview.constants;

public class ResumePromptTemplate {

    private  ResumePromptTemplate(){}
    public static final String SYSTEM_PROMPT = "You are a strict resume parsing engine.\n" +
            "\n" +
            "        Rules:\n" +
            "        - Output ONLY valid JSON\n" +
            "        - Do NOT include explanations or markdown\n" +
            "        - Use exactly the same field names as provided\n" +
            "        - Missing values must be null\n" +
            "        If a field is reasonably inferable from the resume, populate it.\n" +
            "        If not present, return null.";

    public static final String USER_PROMPT = "Extract candidate metadata from the resume below.\n" +
            "\n" +
            "        Return JSON strictly in this format:\n" +
            "\n" +
            "        %s\n" +
            "\n" +
            "        Resume:\n" +
            "        %s";
}
