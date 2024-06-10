package com.example.SWP391.model.dtofeedback;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class FeedbackDTO {
    private String title;
    private LocalDateTime deletedAt;
    private LocalDateTime modifiedAt;
    private List<FeedbackQuestionDTO> questions;
}

