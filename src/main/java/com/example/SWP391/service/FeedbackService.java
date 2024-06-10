package com.example.SWP391.service;

import com.example.SWP391.entity.feedback.Feedback;
import com.example.SWP391.entity.feedback.FeedbackAnswer;
import com.example.SWP391.entity.feedback.FeedbackQuestion;
import com.example.SWP391.model.dtofeedback.FeedbackAnswerDTO;
import com.example.SWP391.model.dtofeedback.FeedbackDTO;

import com.example.SWP391.model.dtofeedback.FeedbackQuestionDTO;
import com.example.SWP391.repository.feedback.FeedBackAwserRepository;
import com.example.SWP391.repository.feedback.FeedBackQuestionRepository;
import com.example.SWP391.repository.feedback.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedBackAwserRepository feedBackAwserRepository;
    @Autowired
    private FeedBackQuestionRepository feedBackQuestionRepository;


    @Transactional
    public Feedback createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setTitle(feedbackDTO.getTitle());
        feedback.setDeleteAt(feedbackDTO.getDeletedAt());
        feedback.setModifiedAt(feedbackDTO.getModifiedAt());

        Feedback savedFeedback = feedbackRepository.save(feedback);

        List<FeedbackQuestionDTO> questions = feedbackDTO.getQuestions();
        if (questions != null) {
            for (FeedbackQuestionDTO questionDTO : questions) {
                FeedbackQuestion question = new FeedbackQuestion();
                question.setTypeQuestion(questionDTO.getTypeQuestion());
                question.setTextQuestion(questionDTO.getTextQuestion());
                question.setDeletedAt(questionDTO.getDeletedAt());
                question.setModifiedAt(questionDTO.getModifiedAt());
                question.setFeedback(savedFeedback);

                FeedbackQuestion savedQuestion = feedBackQuestionRepository.save(question);

                List<FeedbackAnswerDTO> answers = questionDTO.getAnswers();
                if (answers != null) {
                    for (FeedbackAnswerDTO answerDTO : answers) {
                        FeedbackAnswer answer = new FeedbackAnswer();
                        answer.setAnswer(answerDTO.getAnswer());
                        answer.setDeletedAt(answerDTO.getDeletedAt());
                        answer.setModifiedAt(answerDTO.getModifiedAt());
                        answer.setFeedbackQuestion(savedQuestion);

                        feedBackAwserRepository.save(answer);
                    }
                }
            }
        }

        return savedFeedback;
    }
}