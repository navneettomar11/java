package com.nav.springboot.ai.prreviewbot.services;

import com.nav.springboot.ai.prreviewbot.prompts.CodeReviewPromptTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class CodeReviewService {
    private static final Logger logger = LoggerFactory.getLogger(CodeReviewService.class);

    private final ChatClient chatClient;

    public CodeReviewService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public void reviewCode(String repo, String prNumber, String diffUrl) {
        String prompt = CodeReviewPromptTemplate.buildPrompt(repo, prNumber, diffUrl);

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        logger.info("Review Code Response : {}", response);
    }
 }
