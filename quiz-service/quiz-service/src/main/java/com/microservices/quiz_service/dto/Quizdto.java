package com.microservices.quiz_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quizdto {
    private String catagoryName;
    private Integer numQuestion;
    private String title;


}
