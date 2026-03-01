package com.nav.springboot.ai.prreviewbot.controller;


import com.nav.springboot.ai.prreviewbot.services.PullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class GithubWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(GithubWebhookController.class);

    @Autowired
    private PullRequestService pullRequestService;

    @PostMapping("/github")
    public ResponseEntity<Void> handlePR(
            @RequestHeader("X-GitHub-Event") String eventType,
            @RequestBody Map<String, Object> payload) {
        if ("pull_request".equals(eventType) || "synchronize".equals(eventType)) {
            pullRequestService.processPullRequest(payload);
        }
        return ResponseEntity.ok().build();
    }
}
