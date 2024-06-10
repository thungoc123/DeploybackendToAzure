package com.example.SWP391.model.dtofeedback;

import lombok.Data;

import java.time.LocalDateTime;

@Data
 public class FeedbackAnswerDTO {
    private String answer;
    private LocalDateTime deletedAt;
    private LocalDateTime modifiedAt;
}
