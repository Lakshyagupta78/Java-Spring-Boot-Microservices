package com.microservices.quiz_service.controller;

import com.microservices.quiz_service.dto.QuestionWrapper;
import com.microservices.quiz_service.dto.Quizdto;
import com.microservices.quiz_service.dto.Response;
import com.microservices.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService qs;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody Quizdto quizdto){
        return qs.createQuiz(quizdto.getCatagoryName(), quizdto.getNumQuestion(), quizdto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable long id){
        return qs.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitquiz(@PathVariable long id, @RequestBody List<Response> responses){
        return qs.calculateResult(id,responses);
    }

}
