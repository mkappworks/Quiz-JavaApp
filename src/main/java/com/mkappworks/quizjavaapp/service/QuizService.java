package com.mkappworks.quizjavaapp.service;

import com.mkappworks.quizjavaapp.model.Question;
import com.mkappworks.quizjavaapp.model.Quiz;
import com.mkappworks.quizjavaapp.model.QuizQuestion;
import com.mkappworks.quizjavaapp.model.QuizQuestionResponse;
import com.mkappworks.quizjavaapp.repo.QuestionRepo;
import com.mkappworks.quizjavaapp.repo.QuizRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuestionRepo questionRepo;

    public QuizService(QuizRepo quizRepo, QuestionRepo questionRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
    }

    public List<QuizQuestion> getQuizQuestions(Integer id) {
//        Optional<Quiz> quiz = quizRepo.findById(id);
//        if (quiz.isEmpty()) return null;
//
//        List<Question> questionsFromDB = quiz.get().getQuestions();
//        List<QuizQuestion> quizQuestions = new ArrayList<>();
//        for (Question question : questionsFromDB) {
//            QuizQuestion quizQuestion = new QuizQuestion();
//            quizQuestion.setId(question.getId());
//            quizQuestion.setQuestion(question.getQuestion());
//            quizQuestion.setOption1(question.getOption1());
//            quizQuestion.setOption2(question.getOption2());
//            quizQuestion.setOption3(question.getOption3());
//            quizQuestion.setOption4(question.getOption4());
//            quizQuestions.add(quizQuestion);
//        }
//
//        return quizQuestions;

        return quizRepo.findById(id)
                .map(quiz -> quiz.getQuestions().stream()
                        .map(question -> {
                            QuizQuestion quizQuestion = new QuizQuestion();
                            quizQuestion.setId(question.getId());
                            quizQuestion.setQuestion(question.getQuestion());
                            quizQuestion.setOption1(question.getOption1());
                            quizQuestion.setOption2(question.getOption2());
                            quizQuestion.setOption3(question.getOption3());
                            quizQuestion.setOption4(question.getOption4());
                            return quizQuestion;
                        })
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public Quiz createQuiz(String category, int numberOfQuestions, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numberOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);

        return quiz;
    }


    public Integer calculateQuiz(Integer id, List<QuizQuestionResponse> responses) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isEmpty()) return null;

        int numberOfCorrectResponse = 0;
        List<Question> questionsFromDB = quiz.get().getQuestions();
        int i = 0;

        for (QuizQuestionResponse response : responses) {
            if (response.getResponse().equals(questionsFromDB.get(i).getAnswer()))
                numberOfCorrectResponse++;

            i++;
        }

        return numberOfCorrectResponse;
    }
}
