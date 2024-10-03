package com.Quiz.Quiz_Microservices.Service;

import com.Quiz.Quiz_Microservices.Dao.QuestionDao;
import com.Quiz.Quiz_Microservices.Dao.QuizDao;
import com.Quiz.Quiz_Microservices.Model.Question;
import com.Quiz.Quiz_Microservices.Model.QuestionWrapper;
import com.Quiz.Quiz_Microservices.Model.Quiz;
import com.Quiz.Quiz_Microservices.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServices {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int noOfQuestion, String title) {

        List<Question>  questions = questionDao.findRandomQuestionByCategory(category,noOfQuestion);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz  = quizDao.findById(id).get();
        List<Question> questions  = quiz.getQuestions();
        int right =0;
        int i=0;
        for(Response response :responses ){
             if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                 right++;
                 i++;
             }else{
                 i++;
             }

        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
