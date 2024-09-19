package com.mkappworks.quizjavaapp.model;

import lombok.Data;

@Data
public class QuizQuestion {
    private Integer id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
