package com.nav.spring_boot.ai.is.controller;

import com.nav.spring_boot.ai.is.model.EvaluationRequest;
import com.nav.spring_boot.ai.is.model.EvaluationResponse;
import com.nav.spring_boot.ai.is.model.InterviewRequest;
import com.nav.spring_boot.ai.is.model.InterviewResponse;
import com.nav.spring_boot.ai.is.services.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;

    public  InterviewController(InterviewService orchestrator) {
        this.interviewService = orchestrator    ;
    }

    @PostMapping(value = "{sessionId}/start")
    public ResponseEntity<InterviewResponse> start(
            @PathVariable String sessionId,
            @RequestBody InterviewRequest request
            ) {
        return ResponseEntity.ok(interviewService.start(
                request.getRole(),
                request.getDifficulty(),
                request.getKeywords(),
                sessionId));
    }

    @PostMapping(value = "{sessionId}/answer")
    public ResponseEntity<InterviewResponse> answer(
            @PathVariable String sessionId,
            @RequestBody EvaluationRequest request
    ) {
        return ResponseEntity.ok(interviewService.answer(request, sessionId));
    }

    @GetMapping(value = "{sessionId}/report")
    public ResponseEntity<InterviewResponse>  stream(@PathVariable String sessionId) {
        return ResponseEntity.ok(interviewService.report(sessionId));
    }

}
