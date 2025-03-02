package com.konesansjeneral.teste.backend.model.request;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizPlayApiRequest {

    private String quizId;

    private OAuth2AuthenticationToken token;

    private QuizPlayApiRequestBody body;

    public String getCorrectQuestionsNumber() {
        return body.getCorrectQuestionsNumber();
    }

}
