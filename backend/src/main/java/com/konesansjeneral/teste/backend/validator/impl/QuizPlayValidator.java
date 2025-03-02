package com.konesansjeneral.teste.backend.validator.impl;

import com.konesansjeneral.teste.backend.model.entity.QuizPlayEntity;
import com.konesansjeneral.teste.backend.model.request.QuizPlayApiRequest;
import com.konesansjeneral.teste.backend.repository.IUserRepository;
import com.konesansjeneral.teste.backend.validator.IQuizPlayValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class QuizPlayValidator implements IQuizPlayValidator {

    @Autowired
    private CommonValidator validator;

    @Autowired
    private QuizValidator quizValidator;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public QuizPlayEntity validate(QuizPlayApiRequest request) {
        var res = new QuizPlayEntity();
        res.setUser(userRepository.findByAccessToken(request.getToken()));
        res.setQuiz(quizValidator.validateId(request.getQuizId()));
        res.setCorrectQuestionsNumber(validator.validateMandatoryInteger(request.getCorrectQuestionsNumber(), "correctQuestionsNumber"));
        res.setPlayedAt(ZonedDateTime.now());
        return res;
    }

}
