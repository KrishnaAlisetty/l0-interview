/**
 * @author Krishna Alisetty
 * @date 29-12-2025
 */

package com.portal.interview.constants;

public class ResumePromptTemplate {

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
    public static final String GENERATE_QUESTION_PROMPT = "You are a senior technical interviewer designing real-world interview questions.\n" +
            "\n" +
            "Candidate Profile:\n" +
            "- Total Experience: {experience} years\n" +
            "- Primary Skills: {primary_skills}\n" +
            "- Secondary Skills: {secondary_skills}\n" +
            "\n" +
            "Instructions:\n" +
            "1. Generate {count} interview questions tailored to the candidate's experience level.\n" +
            "2. Questions must be practical, scenario-based, and reflect real industry problems.\n" +
            "3. Focus more on primary skills, but include secondary skills where relevant.\n" +
            "4. Adjust difficulty based on experience:\n" +
            "   - 0–2 years: basic concepts + simple scenarios\n" +
            "   - 2–5 years: intermediate + real-world debugging/design\n" +
            "   - 5+ years: advanced system design, scalability, trade-offs\n" +
            "5. Avoid generic or textbook questions.\n" +
            "6. Avoid questions that can be directly copied from common sources.\n" +
            "7. Include a mix of:\n" +
            "   - Conceptual understanding\n" +
            "   - Hands-on coding or debugging scenarios\n" +
            "   - System/design thinking (if experience > 3 years)\n" +
            "8. Questions should test depth, not just definitions.\n" +
            "\n" +
            "Output Format (STRICT JSON):\n" +
            "{\n" +
            "  \"questions\": [\n" +
            "    {\n" +
            "      \"question\": \"string\",\n" +
            "      \"skill\": \"primary/secondary skill name\",\n" +
            "      \"difficulty\": \"easy | medium | hard\",\n" +
            "      \"category\": \"concept | coding | scenario | design\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    public static final String BR_MATCH_PROMPT = "You are an expert technical interviewer and hiring evaluator.\n" +
            "\n" +
            "Your task is to evaluate how well a candidate matches a given job requirement.\n" +
            "\n" +
            "INPUT:\n" +
            "\n" +
            "Candidate Profile:\n" +
            "- Primary Skills: {primary_skills}\n" +
            "- Primary Skills: {secondary_skills}\n" +
            "- Total Experience: {experience} years\n" +
            "\n" +
            "Job Requirement:\n" +
            "{job_description}\n" +
            "\n" +
            "INSTRUCTIONS:\n" +
            "\n" +
            "1. Analyze the candidate's skills and compare them with the required skills in the job description.\n" +
            "2. Consider:\n" +
            "   - Skill match (primary and secondary)\n" +
            "   - Depth of knowledge implied by experience\n" +
            "   - Relevance of skills to the job role\n" +
            "3. Identify:\n" +
            "   - Strong matches\n" +
            "   - Partial matches\n" +
            "   - Missing critical skills\n" +
            "4. Based on the above, calculate an overall match percentage (0–100).\n" +
            "\n" +
            "SCORING GUIDELINES:\n" +
            "- 90–100%% → Strong fit (almost all key skills match)\n" +
            "- 70–89%% → Good fit (most important skills match)\n" +
            "- 50–69%% → Partial fit (some key gaps)\n" +
            "- <50%% → Weak fit (major skill gaps)\n" +
            "\n" +
            "5. Provide a short, clear justification.\n" +
            "\n" +
            "OUTPUT FORMAT (STRICT JSON ONLY):\n" +
            "\n" +
            "{\n" +
            "  \"matchPercentage\": number,\n" +
            "  \"strengths\": [\"list of matched skills\"],\n" +
            "  \"gaps\": [\"list of missing or weak areas\"],\n" +
            "  \"summary\": \"short explanation of why this score was given\"\n" +
            "}";

    private ResumePromptTemplate() {
    }
}
