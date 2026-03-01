package com.nav.springboot.ai.prreviewbot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CodeReviewService {
    private static final Logger logger = LoggerFactory.getLogger(CodeReviewService.class);

    public void reviewCode(String code, Map<String, Object> pr) {
        logger.info("Code Review");
    }
 }
