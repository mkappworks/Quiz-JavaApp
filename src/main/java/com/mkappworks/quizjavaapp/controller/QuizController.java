package com.mkappworks.quizjavaapp.controller;

import com.mkappworks.quizjavaapp.model.Quiz;
import com.mkappworks.quizjavaapp.model.QuizQuestion;
import com.mkappworks.quizjavaapp.model.QuizQuestionResponse;
import com.mkappworks.quizjavaapp.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuizQuestion>> getQuizQuestions(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.getQuizQuestions(id));
    }

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizQuestionResponse> quizQuestionResponses) {
        return new ResponseEntity<>(quizService.calculateQuiz(id, quizQuestionResponses), HttpStatus.OK);
    }

}
