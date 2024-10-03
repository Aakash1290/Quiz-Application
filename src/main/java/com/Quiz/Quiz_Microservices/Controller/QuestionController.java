package com.Quiz.Quiz_Microservices.Controller;

import com.Quiz.Quiz_Microservices.Model.Question;
import com.Quiz.Quiz_Microservices.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @RequestMapping("allQuestions")
    public ResponseEntity<List<Question> > getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @RequestMapping("category/{category}")
    public ResponseEntity<List<Question>>findByQuestionCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity< Question> addQuestion(@RequestBody Question question){
       return  questionService.addQuestion(question);
    }

//@PostMapping("/add")
//public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
//    try {
//        Question savedQuestion = questionService.addQuestion(question);
//        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
//    } catch (Exception e) {
//        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}




}
