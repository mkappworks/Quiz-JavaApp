package com.mkappworks.quizjavaapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizQuestionResponse {
    private Integer id;
    private String response;
}
