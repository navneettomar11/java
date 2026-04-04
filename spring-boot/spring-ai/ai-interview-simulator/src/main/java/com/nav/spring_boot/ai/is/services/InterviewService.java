package com.nav.spring_boot.ai.is.services;

import com.nav.spring_boot.ai.is.model.EvaluationRequest;
import com.nav.spring_boot.ai.is.model.InterviewResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class InterviewService {

    private final ChatClient chatClient;
    public InterviewService(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public InterviewResponse start(String role, String level,String[] keywords, String sessionId) {
        return chatClient.prompt("""
                        You are a strict technical interviewer.
                        Interview Type: %s
                        Difficulty Level: %s
                        Key Technical Keywords: %s
                        Ask ONLY the first question based .
                        Return as JSON:
                        {
                            type: "QUESTION",
                            question: "First question",
                            questionNumber: 1
                        }
                        """.formatted(role, level, String.join(",", Arrays.asList(keywords))))
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, sessionId))
                .call()
                .entity(InterviewResponse.class);

    }

    public InterviewResponse answer(EvaluationRequest request, String sessionId) {
        var role = request.getRole();
        var level = request.getDifficulty();
        var keywords = request.getKeywords();
        var question = request.getQuestion();
        var answer = request.getAnswer();

        return chatClient.prompt("""
               You are a strict and professional technical interviewer.
               Your task is to evaluate the candidate's answer.
               
               Interview Type: %s
               Difficulty Level: %s
               Key Technical Keywords: %s
               Question:
               %s
               
               Candidate Answer:
               %s
            
               Evaluate the answer based on:
               - correctness
               - depth of knowledge
               - clarity
               - completeness
            
               IMPORTANT RULES:
               - Be strict but fair
               - Do not be overly lenient
               - If answer is partially correct, explain what is missing
               - Keep feedback concise but meaningful
            
               Next:
               - Ask a relevant follow-up or next question
               - Adjust difficulty slightly based on performance
            
               OUTPUT FORMAT (STRICT JSON ONLY):
               Do NOT include any explanation, markdown, or extra text.
            
               {
                 "type": "EVALUATION",
                 "feedback": "Short constructive feedback (2-3 lines)",
                 "score": 0-10,
                 "correctAnswer": "Ideal answer in 3-5 lines",
                 "userAnswer": "User Answer",
                 "nextQuestion": "Next interview question",
               }
               """.formatted(role, level, String.join(",", Arrays.asList(keywords)), question, answer))
                .advisors(a -> a.param(CONVERSATION_ID, sessionId))
                .call()
                .entity(InterviewResponse.class);
    }

    public InterviewResponse report(String sessionId) {
        return chatClient.prompt("""
                You are an expert interviewer.
                Generate final report in JSON:
                     {
                       "type": "REPORT",
                       "overallScore": 0-10,
                       "strengths": [string],
                       "weaknesses": [string],
                       "improvementTips": [string],
                       "finalVerdict": "Selected | Rejected"
                     }
                """)
                .advisors(a -> a.param(CONVERSATION_ID, sessionId))
                .call()
                .entity(InterviewResponse.class);
    }
}
