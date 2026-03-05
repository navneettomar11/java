package com.nav.springboot.ai.prreviewbot.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class PostReviewCommentTools {
    private final RestTemplate restTemplate;

    @Value("${GITHUB_TOKEN}")
    private String githubToken;

    @Value("${GITHUB_API_URL}")
    private String githhubApiUrl;

    @Autowired
    public PostReviewCommentTools(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String postReviewComment(String repo, String prNumber, String comment) {
        String url = """
                https://api.github.com/repos/%s/issues/%s/comments
               """.formatted(repo, prNumber);

        Map<String, String> body = Map.of("body", comment);

        restTemplate
                .postForEntity(url, body, String.class);

        return "Comment posted successfully";
    }
}
