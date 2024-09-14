package com.microservices.question_service.dto;

import lombok.Data;

@Data
public class Response {
    private long id;
    private String response;

    public Response(long id, String response) {
        this.id = id;
        this.response = response;
    }
}
