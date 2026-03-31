/**
 * @author Krishna Alisetty
 * @date 31-03-2026
 */

package com.portal.interview.service;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.stereotype.Service;


@Service
public class TextToSpeechService {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public TextToSpeechService(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    public byte[] getSpeechData(String text) {
        return openAiAudioSpeechModel.call(text);
    }
}
