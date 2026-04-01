/**
 * @author Krishna Alisetty
 * @date 29-12-2025
 */

package com.portal.interview.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.interview.ai.ResumePromptBuilder;
import com.portal.interview.dto.BRResponse;
import com.portal.interview.dto.CandidateProfile;
import com.portal.interview.dto.Question;
import com.portal.interview.dto.QuestionResponse;
import com.portal.interview.entity.BusinessRequirementEntity;
import com.portal.interview.entity.Candidate;
import com.portal.interview.mapper.CandidateProfileMapper;
import com.portal.interview.repository.BusinessRequirementRepository;
import com.portal.interview.repository.CandidateRepository;
import com.portal.interview.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ExtractInfoService {

    private CandidateRepository candidateRepository;

    private ChatClient chatClient;

    private ResumePromptBuilder resumePromptBuilder = new ResumePromptBuilder();

    private ObjectMapper mapper = new ObjectMapper();

    private CandidateProfileMapper candidateProfileMapper;

    private QuestionsRepository questionsRepository;

    private BusinessRequirementRepository businessRequirementRepository;

    private static Map<String, String> jobDescriptions = new HashMap<>();

    static {
        jobDescriptions.put("Java full stack", "The Tech Lead will provide design " +
                "and delivery of a portal web application within the agile development lifecycle. " +
                "The ideal candidate will be an experienced engineer with web application development with micro services and REST APIs " +
                "in highly performant systems.  The candidate has at least 5 years of experience developing a web application. To be successful, " +
                "the candidate needs to show experience with Java, springboot, Microservices and Javascript Frameworks, e.g., " +
                "Angular, REACT etc. and REST APIs. The candidate should also have Cloud-based development experience.");

        jobDescriptions.put("Python developer", "Write python code in an object oriented manner or in Prefect / Snowflake / Databricks to retrieve medical record from third party solution.Databricks: Mastery\n" +
                "Kubernetes: Mastery\n" +
                "Python: Mastery");
    }


    @Autowired
    public ExtractInfoService(ChatClient chatClient, CandidateRepository candidateRepository, QuestionsRepository questionsRepository, BusinessRequirementRepository businessRequirementRepository) {
        this.candidateRepository = candidateRepository;
        this.chatClient = chatClient;
        this.candidateProfileMapper = new CandidateProfileMapper();
        this.questionsRepository = questionsRepository;
        this.businessRequirementRepository = businessRequirementRepository;
    }

    public Candidate extractResumeInfo(String info) {
        resumePromptBuilder = resumePromptBuilder.withResumeText(info);
        String data = chatClient.prompt().system(
                resumePromptBuilder.buildSystemPrompt()
        ).user(u -> {
            u.text(resumePromptBuilder.buildUserPrompt());
        }).call().content();

        try {
            CandidateProfile candidateProfile = mapper.readValue(data, CandidateProfile.class);
            return candidateProfileMapper.toEntity(candidateProfile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Async
    @Transactional
    public void prepareQuestionsForCandidate(Float experience, String primary_skills, String secondary_skills, Long candidateId) {
        QuestionResponse data = chatClient.prompt().system(
                resumePromptBuilder.buildQuestionGenerationPrompt(experience, primary_skills, secondary_skills, 5)
        ).call().entity(QuestionResponse.class);

        List<Question> questions = data.questions();
        Set<com.portal.interview.entity.Question> questionSet = new HashSet<>();
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            questions.forEach(question -> {
                com.portal.interview.entity.Question q = new com.portal.interview.entity.Question();
                q.setCategory(question.category());
                q.setQuestion(question.question());
                q.setDifficulty(question.difficulty());
                q.setSkill(question.skill());
                q.setCandidate(candidate.get());
                questionSet.add(q);
            });
        }

        questionsRepository.saveAll(questionSet);
    }

    @Async
    @Transactional
    public void saveAndCalculateBRMatch(Float experience, String primary_skills, String secondary_skills, Long candidateId, String brNumber) {
        String jobDescription = "Write python code in an object oriented manner or in Prefect / Snowflake / Databricks to retrieve medical record from third party solution.Databricks: Mastery\n" +
                "Kubernetes: Mastery\n" +
                "Python: Mastery";
        BRResponse brResponse = chatClient.prompt().system(
                resumePromptBuilder.buildBRMatchWithSkillSet(experience, primary_skills, secondary_skills, jobDescription)
        ).call().entity(BRResponse.class);

        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            BusinessRequirementEntity businessRequirementEntity = new BusinessRequirementEntity();
            businessRequirementEntity.setBrId(brNumber);
            businessRequirementEntity.setStrengths(brResponse.strengths().stream().collect(Collectors.joining(",")));
            businessRequirementEntity.setGaps(brResponse.gaps().stream().collect(Collectors.joining(",")));
            businessRequirementEntity.setSummary(brResponse.summary());
            businessRequirementEntity.setPercentage(brResponse.matchPercentage());

            businessRequirementEntity.setCandidate(candidate.get());

            businessRequirementRepository.save(businessRequirementEntity);
        }

    }
}
