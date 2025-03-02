package com.konesansjeneral.teste.backend.validator;

import com.konesansjeneral.teste.backend.model.entity.QuizEntity;
import com.konesansjeneral.teste.backend.model.request.QuizApiRequest;

public interface IQuizValidator {

    QuizEntity validateId(String id);

    QuizEntity validate(QuizApiRequest request);

}
