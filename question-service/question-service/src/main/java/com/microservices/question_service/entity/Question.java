package com.microservices.question_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "Question")
@Data
@Entity
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "question_Title")
    private String questionTitle;

    @Column(name = "option1")
    private String option1;
    @Column(name = "option2")
    private String option2;
    @Column(name = "option3")
    private String option3;
    @Column(name = "option4")
    private String option4;
    @Column(name = "right_Answer")
    private String rightAnswer;
    @Column(name = "difficulty_Level")
    private String difficultyLevel;
    @Column(name = "catagory")
    private String catagory;

    public Question(long id, String questionTitle, String option1, String option2, String option3, String option4, String rightAnswer, String difficultyLevel, String catagory) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.rightAnswer = rightAnswer;
        this.difficultyLevel = difficultyLevel;
        this.catagory = catagory;
    }

    public Question() {
    }
}
