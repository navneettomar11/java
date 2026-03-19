# AI Interview Simulator

A high-impact, real-world portfolio project that simulates technical/HR interviews using LLMs + voice + evaluation engine—perfect for showcasing Spring AI, multimodal, and system design skills.

## 🚀 What You’re Building
An app where users:
- Select role (Java Dev, Frontend, DevOps…)
- Choose experience level
- Attend a live AI interview
- Get feedback, score, and improvement tips

## 🧠 Key Features
### 🎤 1. Voice-Based Interview (Multimodal)
 - Speak answers → converted to text
 - AI asks next question dynamically
 Tech 
 - Whisper (Speech-to-Text)
 - TTS (Text-to-Speech)
### 💬 2. Intelligent Interview Chat Engine
- Context-aware follow-up questions
- Role-based questioning
- Adaptive difficulty
Example:
> AI: “Explain microservices”
> User: answers
> AI: “What challenges did you face?”
### 🧑‍⚖️ 3. AI Evaluation Engine
Scores:
- Technical knowledge
    - Communication
    - Confidence
- Provides:
  - Strengths
  - Weaknesses
  - Suggested answers
    answers

### 📊 4. Performance Dashboard
- Score trends
- Improvement tracking
- Interview history
### 🧠 5. Multi-Agent System (🔥 Advanced)
Different AI roles:
- Interviewer Agent
- Evaluator Agent
- HR Agent
- Feedback Coach

## 🏗️ Architecture
```
Frontend (React + Bootstrap)
        |
        v
Spring Boot (Spring AI)
        |
 ┌───────────────┬───────────────┬───────────────┐
 |               |               |
Chat Model   Speech Model    Evaluation Engine
(OpenAI)     (Whisper)       (Custom prompts)
 |
Vector DB (optional for Q bank)
 |
PostgreSQL
```
## ⚙️ Tech Stack
Backend
- Spring Boot 3
- Spring AI
- WebFlux (streaming responses)
- PostgreSQL

AI Models
- GPT-4o / GPT-4o-mini
- Whisper (speech-to-text)
- TTS (voice output)

## 📂 Modules Design
1. **interview-service**
- Start interview
- Manage sessions
- Track questions

2. **ai-engine**
- Generate questions
- Evaluate answers
- Feedback generation

3. **speech-service**
- Audio → text
- Text → audio

4. **analytics-service**
- Scores
- Reports

## 🔁 Interview Flow
```
Start Interview
   ↓
Select Role + Experience
   ↓
AI asks question
   ↓
User answers (voice/text)
   ↓
AI follow-up question
   ↓
Repeat (5–10 rounds)
   ↓
Evaluation + Scorecard
```