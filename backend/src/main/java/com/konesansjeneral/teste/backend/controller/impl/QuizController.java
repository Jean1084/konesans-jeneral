package com.konesansjeneral.teste.backend.controller.impl;

import com.konesansjeneral.teste.backend.controller.IQuizController;
import com.konesansjeneral.teste.backend.model.entity.QuizEntity;
import com.konesansjeneral.teste.backend.model.request.QuizApiRequest;
import com.konesansjeneral.teste.backend.model.response.QuizApiResponse;
import com.konesansjeneral.teste.backend.repository.IQuizRepository;
import com.konesansjeneral.teste.backend.utils.JsonUtils;
import com.konesansjeneral.teste.backend.validator.impl.QuizValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@RestController
public class QuizController implements IQuizController {

    @Autowired
    private QuizValidator validator;

    @Autowired
    private IQuizRepository repository;


    @GetMapping("/quizzes")
    public List<QuizApiResponse> getAll() {
        return repository.findAll().stream().map(QuizEntity::toApiResponse).toList();
    }

    @GetMapping("/quizzes/{id}")
    public QuizApiResponse getById(@PathVariable String id) {
        var entity = validator.validateId(id);
        return entity.toApiResponse();
    }

    @PostMapping("/quizzes")
    public QuizApiResponse create(@RequestBody QuizApiRequest request) {
        var entity = validator.validate(request);
        repository.save(entity);
        return entity.toApiResponse();
    }

    @DeleteMapping("/quizzes/{id}")
    public void delete(@PathVariable String id) {
        var entity = validator.validateId(id);
        repository.delete(entity);
    }

    @PutMapping("/quizzes/{id}")
    public void update(@PathVariable String id, @RequestBody QuizApiRequest request) {
        delete(id);
        create(request);
    }

    @GetMapping("/quizzes/export")
    public ResponseEntity<byte[]> exportQuizzes() {
        var quizzes = getAll();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=quizzes.json")
                .body(JsonUtils.writeValueAsBytes(quizzes));
    }

    @Transactional
    @PostMapping(value = "/quizzes/import", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void importQuizzes(@RequestPart MultipartFile file) {
        var request = JsonUtils.readValue(file, QuizApiRequest[].class);
        var quizzes = Arrays.stream(request).map(validator::validate).toList();
        quizzes.stream().forEach(quiz -> repository.deleteByTitle(quiz.getTitle()));
        repository.saveAll(quizzes);
    }

}
