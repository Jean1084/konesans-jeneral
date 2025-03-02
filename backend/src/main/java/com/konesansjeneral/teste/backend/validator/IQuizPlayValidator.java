package com.konesansjeneral.teste.backend.validator;

import com.konesansjeneral.teste.backend.model.entity.QuizPlayEntity;
import com.konesansjeneral.teste.backend.model.request.QuizPlayApiRequest;


public interface IQuizPlayValidator {

    QuizPlayEntity validate(QuizPlayApiRequest request);

}
