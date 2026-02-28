package com.nav.spring_boot.ai.rr.controller;

import com.nav.spring_boot.ai.rr.dto.ResumeReview;
import com.nav.spring_boot.ai.rr.services.ResumeReviewService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
public class ResumeReviewerController {

    @Autowired
    private ResumeReviewService resumeReviewService;

    @PostMapping("/review")
    public ResumeReview review(@RequestParam("file") MultipartFile file,
                               @RequestParam("targetRole") String targetRole){

        return this.resumeReviewService.review(file, targetRole);
    }

}
