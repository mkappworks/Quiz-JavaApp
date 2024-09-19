package com.mkappworks.quizjavaapp.repo;

import com.mkappworks.quizjavaapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
