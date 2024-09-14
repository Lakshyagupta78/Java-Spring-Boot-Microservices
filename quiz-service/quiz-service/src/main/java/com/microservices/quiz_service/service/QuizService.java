package com.microservices.quiz_service.service;

import com.microservices.quiz_service.dao.Quizdao;
import com.microservices.quiz_service.dto.QuestionWrapper;
import com.microservices.quiz_service.dto.Response;
import com.microservices.quiz_service.entity.Quiz;
import com.microservices.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    Quizdao qd;

    @Autowired
    QuizInterface qi;

    public ResponseEntity<String> createQuiz(String catagoryName, Integer numQ, String title) {
        List<Long> questions = qi.getQuestionForQuiz(catagoryName,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        qd.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(long id) {
        Quiz quiz = qd.findById(id).get();
        List<Long> questionsIds = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> questions = qi.getQuestionFromId(questionsIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(long id, List<Response> responses) {
        ResponseEntity<Integer> result = qi.getScore(responses);
        return result;
    }
}
