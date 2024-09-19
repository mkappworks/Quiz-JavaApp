package com.mkappworks.quizjavaapp.controller;

import com.mkappworks.quizjavaapp.service.QuestionService;
import com.mkappworks.quizjavaapp.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestion(@PathVariable String id) {
        Optional<Question> question = questionService.getQuestionById(Integer.parseInt(id));
        if (question.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        return ResponseEntity.ok(question);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.saveQuestion(question), HttpStatus.OK);
    }
}
