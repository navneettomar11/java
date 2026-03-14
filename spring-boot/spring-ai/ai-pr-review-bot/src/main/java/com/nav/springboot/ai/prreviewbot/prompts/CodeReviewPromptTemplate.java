package com.nav.springboot.ai.prreviewbot.prompts;

public class CodeReviewPromptTemplate {

    public static String buildPrompt(String diffUrl, String repo, String prNumber) {
        return """
        You are a senior software engineer.
        Steps:
        1. Call get_pr_diff with the diffUrl.
        2. Analyze the code carefully and provides bugs, code smells, performance issues, security issues, and suggested improvements .
        3. If issues are found, call post_review_comment.
        4. Include structured and clear feedback.
        PR Diff URL: %s
        Repo: %s
        PR Number: %s
        """.formatted(diffUrl, repo, prNumber);
    }
}