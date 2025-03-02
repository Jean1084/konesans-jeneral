package com.konesansjeneral.teste.backend.repository;

import com.konesansjeneral.teste.backend.model.entity.QuizPlayEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuizPlayRepository extends JpaRepository<QuizPlayEntity, QuizPlayEntity.PrimaryKey> {

}

