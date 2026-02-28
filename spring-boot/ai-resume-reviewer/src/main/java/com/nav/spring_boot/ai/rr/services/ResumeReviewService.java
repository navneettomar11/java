package com.nav.spring_boot.ai.rr.services;

import com.nav.spring_boot.ai.rr.dto.ResumeReview;
import com.nav.spring_boot.ai.rr.utils.TextExtractor;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeReviewService {

    private final ChatClient chatClient;

    @Autowired
    private TextExtractor textExtractor;

    public ResumeReviewService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    public ResumeReview review(MultipartFile file, String targetRole) {
        try {
            String resumeText = this.textExtractor.extractText(file);
            String prompt = """
            You are an expert technical recruiter.
            
            Analyze the following resume for the role of %s.
            
            Evaluate:
            - Technical skills match
            - Experience relevance
            - ATS optimization
            - Clarity and impact
            
            Return:
            Return strictly valid JSON.
            Each field must appear only once.
            Do not repeat any keys.
            - Overall score (0-100)
            - Strengths
            - Weaknesses
            - Missing keywords
            - Suggestions
            - Summary
            
            Resume:
            %s
            """.formatted(targetRole, resumeText);
            return this.chatClient
                    .prompt()
                    .system("You are a resume evaluator.")
                    .user(prompt)
                    .call()
                    .entity(ResumeReview.class);
        } catch (Exception ex) {
            System.err.println("Error occured" + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

}
