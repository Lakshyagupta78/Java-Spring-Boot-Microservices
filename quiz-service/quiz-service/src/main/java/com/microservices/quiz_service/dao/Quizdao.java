package com.microservices.quiz_service.dao;

import com.microservices.quiz_service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Quizdao extends JpaRepository<Quiz, Long> {

}
