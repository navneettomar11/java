package com.nav.spring_boot.ai.rr.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ResumeReview(

        @JsonProperty("overallScore")
        int overallScore,

        @JsonProperty("strengths")
        List<String> strengths,

        @JsonProperty("weaknesses")
        List<String> weaknesses,

        @JsonProperty("missingKeywords")
        List<String> missingKeywords,

        @JsonProperty("suggestions")
        List<String> suggestions,

        @JsonProperty("summary")
        String summary
) {}