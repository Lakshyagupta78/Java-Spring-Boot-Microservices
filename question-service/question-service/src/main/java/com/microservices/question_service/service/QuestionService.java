package com.microservices.question_service.service;

import com.microservices.question_service.dao.QuestionDao;
import com.microservices.question_service.dto.QuestionWrapper;
import com.microservices.question_service.dto.Response;
import com.microservices.question_service.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao qd;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(qd.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCatagory(String catagory) {
        try{
            return new ResponseEntity<>(qd.findByCatagory(catagory), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(List<Question> question) {
        try{
            qd.saveAll(question);
            return new ResponseEntity<>("Question added successfully!" ,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<List<Long>> getQuestionForQuiz(String catagoryName, int numQ) {
        List<Long> question = qd.findRandomQuestionsByCatagory(catagoryName,numQ);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Long> questionid) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(long id : questionid){
            questions.add(qd.findById(id).get());
        }
        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int result = 0;
        for(Response response : responses){
            Question question = qd.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                result++;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
