package com.konesansjeneral.teste.backend.repository;

import com.konesansjeneral.teste.backend.model.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IQuizRepository extends JpaRepository<QuizEntity, UUID> {

    Optional<QuizEntity> findByTitle(String title);

    void deleteByTitle(String title);

}
