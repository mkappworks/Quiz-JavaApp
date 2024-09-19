package com.mkappworks.quizjavaapp.service;

import com.mkappworks.quizjavaapp.model.Question;
import com.mkappworks.quizjavaapp.repo.QuestionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Optional<Question> getQuestionById(int id) {
        return questionRepo.findById(id);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findAllByCategory(category);
    }

    public Question saveQuestion(Question question) {
        return questionRepo.save(question);
    }

    public void deleteQuestionById(int id) {
        questionRepo.deleteById(id);
    }
}
