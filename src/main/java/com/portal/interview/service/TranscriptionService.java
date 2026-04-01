/**
 * @author Krishna Alisetty
 * @date 01-04-2026
 */

package com.portal.interview.service;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

;


@Service
public class TranscriptionService {

    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public TranscriptionService(OpenAiAudioTranscriptionModel transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }

    public String transcribe(MultipartFile file) {

        AudioTranscriptionPrompt prompt = null;
        try {
            prompt = new AudioTranscriptionPrompt(
                    new ByteArrayResource(file.getBytes()) {
                        @Override
                        public String getFilename() {
                            return file.getOriginalFilename();
                        }
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transcriptionModel.call(prompt)
                .getResult()
                .getOutput();
    }
}
