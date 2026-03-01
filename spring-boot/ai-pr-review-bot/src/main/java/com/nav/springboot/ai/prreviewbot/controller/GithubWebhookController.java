package com.nav.springboot.ai.prreviewbot.controller;


import com.nav.springboot.ai.prreviewbot.services.PullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class GithubWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(GithubWebhookController.class);

    @Autowired
    private PullRequestService pullRequestService;

    @PostMapping("/github")
    public ResponseEntity<Void> handlePR(@RequestBody Map<String, Object> payload) {
        String action = (String) payload.get("action");
        if ("opened".equals(action) || "synchronize".equals(action)) {
            pullRequestService.processPullRequest(payload);
        }
        return ResponseEntity.ok().build();
    }
}
