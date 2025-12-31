package com.portal.interview.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ResumeParsingService {

    private final Tika tika = new Tika();

    public String parseResume(MultipartFile resumeContent) {
        try (InputStream inputStream = resumeContent.getInputStream()) {
            AutoDetectParser autoDetectParser = new AutoDetectParser();
            BodyContentHandler bodyContentHandler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();
            ParseContext parseContext = new ParseContext();

            autoDetectParser.parse(inputStream, bodyContentHandler, metadata, parseContext);
            String resumeText = bodyContentHandler.toString();
            return resumeText;
        } catch (IOException e) {
            throw new RuntimeException("Error parsing resume", e);
        } catch (TikaException e) {
            throw new RuntimeException("Error parsing resume", e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
