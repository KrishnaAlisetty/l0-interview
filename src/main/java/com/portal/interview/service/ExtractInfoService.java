/**
 * @author Krishna Alisetty
 * @date 29-12-2025
 */

package com.portal.interview.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.interview.ai.ResumePromptBuilder;
import com.portal.interview.dto.CandidateProfile;
import com.portal.interview.entity.Candidate;
import com.portal.interview.mapper.CandidateProfileMapper;
import com.portal.interview.repository.CandidateRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExtractInfoService {

    private CandidateRepository candidateRepository;

    private ChatClient chatClient;

    private ResumePromptBuilder resumePromptBuilder = new ResumePromptBuilder();

    private ObjectMapper mapper = new ObjectMapper();

    private CandidateProfileMapper candidateProfileMapper;

    @Autowired
    public ExtractInfoService(ChatClient chatClient, CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
        this.chatClient = chatClient;
        this.candidateProfileMapper = new CandidateProfileMapper();
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
            //Candidate candidate  = candidateRepository.save(candidateProfileMapper.toEntity(candidateProfile));
            return candidateProfileMapper.toEntity(candidateProfile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
