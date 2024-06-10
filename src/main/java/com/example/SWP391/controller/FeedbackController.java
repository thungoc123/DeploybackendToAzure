package com.example.SWP391.controller;

import com.example.SWP391.entity.feedback.Feedback;
import com.example.SWP391.model.dtofeedback.FeedbackDTO;
import com.example.SWP391.service.FeedbackService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback createdFeedback = feedbackService.createFeedback(feedbackDTO);
        return ResponseEntity.ok(createdFeedback);
    }
}