package com.nav.springboot.ai.prreviewbot.prompts;

public class CodeReviewPromptTemplate {

    public static String buildPrompt(String diff) {
        return """
        You are a senior software engineer.

        Analyze the following GitHub PR diff and provide:

        1. Bugs
        2. Code smells
        3. Performance issues
        4. Security risks
        5. Suggested improvements

        Return JSON in this format:

        {
          "summary": "...",
          "issues": [
            {
              "file": "",
              "line": "",
              "severity": "",
              "comment": "",
              "suggestion": ""
            }
          ]
        }

        PR Diff:
        """ + diff;
    }
}