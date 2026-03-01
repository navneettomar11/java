package com.nav.springboot.ai.prreviewbot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class PullRequestService {

    private static final Logger logger = LoggerFactory.getLogger(PullRequestService.class);

    @Value("${GITHUB_TOKEN}")
    private String githubToken;

    @Autowired
    private WebClient webClient;

    @Autowired
    private CodeReviewService reviewService;

    public void processPullRequest(Map<String, Object> payload) {
        Map<String, Object> pr = (Map<String, Object>) payload.get("pull_request");
        String diffUrl = (String) pr.get("diff_url");
        String diff = webClient.get()
                .uri(diffUrl)
                .header("Authorization", "Bearer " + this.githubToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        reviewService.reviewCode(diff, pr);
    }
}
