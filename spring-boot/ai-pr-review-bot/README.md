# Github PR Code Reviewer

## Project Goal
Automatically review GitHub Pull Requests using LLMs and post structured feedback directly on the PR.

It will:
- Fetch PR diff from GitHub
- Analyze code using Spring AI
- Generate structured review
- Post comments back to GitHub

## High-Level Architecture
GitHub Webhook → Spring Boot App
↓
PR Diff Fetcher
↓
Spring AI (LLM)
↓
Structured Review JSON
↓
GitHub Comment API


## Tech Stack
- Spring Boot 3.x
- Spring AI
- GitHub REST API
- OpenAI 
- Webhook endpoint