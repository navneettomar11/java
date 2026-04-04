package com.nav.spring_boot.ai.is.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class InterviewResponse {

    private String type; // QUESTION / EVALUATION / REPORT

    //QUESTION
    private String question;

    //EVALUATION
    private String nextQuestion;
    private String userAnswer;
    private String feedback;
    private int score;
    private String correctAnswer;

    //REPORT
    private int overallScore;
    private String[] strengths;
    private String[] weaknesses;
    private String[] improvementTips;
    private String finalVerdict;
}
