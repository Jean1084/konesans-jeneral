package com.konesansjeneral.teste.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuizQuestionApiResponse {

    private String question;
    private List<String> options = new ArrayList<>();
    private Integer answerIndex;

}
