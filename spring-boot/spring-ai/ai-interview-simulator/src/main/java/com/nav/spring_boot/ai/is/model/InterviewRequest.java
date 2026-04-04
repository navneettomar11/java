package com.nav.spring_boot.ai.is.model;

import lombok.Data;

@Data
public class InterviewRequest {
    protected String role;
    protected String difficulty;
    protected Integer round;
    protected String[] keywords;
    protected Integer questionNumber;
}
