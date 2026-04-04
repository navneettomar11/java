package com.nav.spring_boot.ai.is.model;

import lombok.Data;

@Data
public class EvaluationRequest extends InterviewRequest{
    private String question;
    private String answer;
}
