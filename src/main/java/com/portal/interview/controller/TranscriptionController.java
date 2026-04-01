/**
 * @author Krishna Alisetty
 * @date 01-04-2026
 */

package com.portal.interview.controller;

import com.portal.interview.service.TranscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/stt")
@CrossOrigin(origins = "http://localhost:5173")
public class TranscriptionController {

    private final TranscriptionService transcriptionService;

    public TranscriptionController(TranscriptionService transcriptionService) {
        this.transcriptionService = transcriptionService;
    }

    @PostMapping("/transcribe")
    public ResponseEntity<?> transcribe(@RequestParam("file") MultipartFile file) {

        String text = transcriptionService.transcribe(file);

        return ResponseEntity.ok().body(
                Map.of("text", text)
        );
    }
}
