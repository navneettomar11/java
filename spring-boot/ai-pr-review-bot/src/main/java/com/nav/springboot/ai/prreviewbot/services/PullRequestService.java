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
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PullRequestService {

    private static final Logger logger = LoggerFactory.getLogger(PullRequestService.class);

    @Value("${GITHUB_TOKEN}")
    private String githubToken;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CodeReviewService reviewService;

    public void processPullRequest(Map<String, Object> payload) {
        Map<String, Object> pr = (Map<String, Object>) payload.get("pull_request");
        String diffUrl = (String) pr.get("diff_url");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.githubToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response =
                restTemplate.exchange(diffUrl, HttpMethod.GET, entity, String.class);
        String diff = response.getBody();
        reviewService.reviewCode(diff, pr);
    }
}
