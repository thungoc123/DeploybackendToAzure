package com.example.SWP391.repository.feedback;

import com.example.SWP391.entity.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
}
