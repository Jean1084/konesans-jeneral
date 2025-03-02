package com.konesansjeneral.teste.backend.model.request;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
public class QuizQuestionApiRequest {

    private String question;

    private List<String> options = new ArrayList<>();

    @Schema(implementation = Integer.class)
    private String answerIndex;

}
