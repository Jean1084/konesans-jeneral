package com.konesansjeneral.teste.backend.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuizApiRequest {

    private String title;
    private String description;
    private List<String> tags;
    private String thumbnail;
    private List<QuizQuestionApiRequest> questions = new ArrayList<>();

}

