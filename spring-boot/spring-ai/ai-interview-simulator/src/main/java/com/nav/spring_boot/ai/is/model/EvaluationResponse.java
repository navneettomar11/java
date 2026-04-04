package com.nav.spring_boot.ai.is.model;

import lombok.Data;

@Data
public class EvaluationResponse {

    private String nextQuestion;
    private String userAnswer;
    private String feedback;
    private int score;
    private String correctAnswer;
    private int questionNumber;



}
