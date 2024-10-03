package com.Quiz.Quiz_Microservices.Dao;

import com.Quiz.Quiz_Microservices.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {


}
