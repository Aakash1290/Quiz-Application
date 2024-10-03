package com.Quiz.Quiz_Microservices.Controller;

import com.Quiz.Quiz_Microservices.Model.Question;
import com.Quiz.Quiz_Microservices.Model.QuestionWrapper;
import com.Quiz.Quiz_Microservices.Model.Response;
import com.Quiz.Quiz_Microservices.Service.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizServices quizServices;

    @PostMapping("/create")
    public ResponseEntity<String>  createQuiz(@RequestParam String category,@RequestParam int noOfQuestion,@RequestParam String title){
     return   quizServices.createQuiz(category,noOfQuestion,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizServices.getQuizQuestions(id);

    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
                    return quizServices.calculateResult(id,response);
    }




}
