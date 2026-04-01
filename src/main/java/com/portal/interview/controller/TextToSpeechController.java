/**
 * @author Krishna Alisetty
 * @date 31-03-2026
 */

package com.portal.interview.controller;

import com.portal.interview.service.TextToSpeechService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tts")
@CrossOrigin(origins = "http://localhost:5173")
public class TextToSpeechController {

    private TextToSpeechService textToSpeechService;

    public TextToSpeechController(TextToSpeechService textToSpeechService) {
        this.textToSpeechService = textToSpeechService;
    }

    @RequestMapping
    public ResponseEntity<byte[]> speechData(@RequestParam String text) {
        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(textToSpeechService.getSpeechData(text));
    }
}
