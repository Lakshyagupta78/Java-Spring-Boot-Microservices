package com.microservices.question_service.Controller;

//import org.springframework.stereotype.Controller;
import com.microservices.question_service.dto.QuestionWrapper;
import com.microservices.question_service.dto.Response;
import com.microservices.question_service.entity.Question;
import com.microservices.question_service.service.QuestionService;
//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService qs;

    @Autowired
    Environment environment;

    @GetMapping("allQuerstion")
    public ResponseEntity<List<Question>> getAllquestion(){
        return qs.getAllQuestion();
    }

    @GetMapping("catagory/{catagory}")
    public ResponseEntity<List<Question>> getQuestionByCatagory(@PathVariable String catagory){
        return qs.getQuestionByCatagory(catagory);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody List<Question> question) {
        return qs.addQuestion(question);
    }


    //generate
    //getQuestion (questionid)
    //getScore

    @GetMapping("generate")
    public ResponseEntity<List<Long>> getQuestionForQuiz(@RequestParam String catagoryName,@RequestParam int numQ){
        return qs.getQuestionForQuiz(catagoryName,numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Long> questionid){
        System.out.println(environment.getProperty("local.server.port"));
        return qs.getQuestionFromId(questionid);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
         return qs.getScore(responses);
    }
}
