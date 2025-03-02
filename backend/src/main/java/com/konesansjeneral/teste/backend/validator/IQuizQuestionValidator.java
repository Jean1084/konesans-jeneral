package com.konesansjeneral.teste.backend.validator;

import com.konesansjeneral.teste.backend.model.entity.QuizEntity;
import com.konesansjeneral.teste.backend.model.entity.QuizQuestionEntity;
import com.konesansjeneral.teste.backend.model.request.QuizQuestionApiRequest;

public interface IQuizQuestionValidator {

    QuizQuestionEntity validate(QuizQuestionApiRequest request, QuizEntity quiz);

}
