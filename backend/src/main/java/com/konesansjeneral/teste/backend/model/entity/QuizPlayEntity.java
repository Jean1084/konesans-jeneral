package com.konesansjeneral.teste.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.konesansjeneral.teste.backend.model.response.QuizPlayApiResponse;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "T_QUIZ_PLAY")
@IdClass(QuizPlayEntity.PrimaryKey.class)
public class QuizPlayEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUIZ_ID", referencedColumnName = "ID")
    private QuizEntity quiz;

    @Column(name = "PLAYED_AT")
    private ZonedDateTime playedAt;

    @Column(name = "CORRECT_QUESTIONS_NUMBER")
    private Integer correctQuestionsNumber;

    public QuizPlayApiResponse toApiResponse() {
        var res = new QuizPlayApiResponse();
        res.setQuizId(getQuiz().getId());
        res.setQuizTitle(getQuiz().getTitle());
        res.setPlayedAt(getPlayedAt().withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
        res.setQuestionsNumber(getQuiz().getQuestions().size());
        res.setCorrectQuestionsNumber(getCorrectQuestionsNumber());
        return res;
    }

    @Getter
    @Setter
    public static class PrimaryKey {
        private UserEntity user;
        private QuizEntity quiz;
    }

}
