package com.microservices.question_service.dao;

import com.microservices.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {
    List<Question> findByCatagory(String catagory);

//    @Query(value="SELECT q.id FROM question q where q.catagoryName =: catagory ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true )
//    List<Long> findRandomQuestionsByCatagory(String catagoryName, int numQ);

    @Query(value = "SELECT q.id FROM question q WHERE q.catagory = :catagoryName ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Long> findRandomQuestionsByCatagory(@Param("catagoryName") String catagoryName, @Param("numQ") int numQ);


}
