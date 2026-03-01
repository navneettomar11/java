package com.nav.springboot.ai.prreviewbot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook/github")
public class GithubWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(GithubWebhookController.class);

    @PostMapping("")
    public ResponseEntity<Void> handlePR(@RequestBody Map<String, Object> payload) {
        logger.info("Payload: {} ", payload);
        return ResponseEntity.ok().build();
    }
}
