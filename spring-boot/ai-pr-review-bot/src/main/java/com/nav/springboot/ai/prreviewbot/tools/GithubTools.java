package com.nav.springboot.ai.prreviewbot.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubTools{

    private final RestTemplate restTemplate;

    @Value("${GITHUB_TOKEN}")
    private String githubToken;


    @Autowired
    public GithubTools(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Tool(name="get_pr_diff", description = "Fetches the diff of a Github pull request")
    public String getPrDiff(String diffUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("GITHUB_TOKEN");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                diffUrl,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();
    }
}
