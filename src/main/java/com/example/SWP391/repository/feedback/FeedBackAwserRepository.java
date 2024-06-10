package com.example.SWP391.repository.feedback;

import com.example.SWP391.entity.feedback.FeedbackAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackAwserRepository extends JpaRepository<FeedbackAnswer,Integer> {
}
