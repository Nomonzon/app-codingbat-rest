package com.example.appcodingbatrest.controllers;

import com.example.appcodingbatrest.entities.Answer;
import com.example.appcodingbatrest.payload.AnswerDto;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public ResponseEntity<?> getAllAnswer(){
        List<Answer> answers = answerService.getAllAnswersFromDataBase();
        return answers != null && !answers.isEmpty()
                ? new ResponseEntity<>(answers, HttpStatus.OK)
                : new ResponseEntity<>("Answers Not found", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnswerById(@PathVariable Long id){
        Answer answerById = answerService.getAnswerById(id);
        return answerById != null
                ? new ResponseEntity<>(answerById, HttpStatus.OK)
                : new ResponseEntity<>("Answer not found by this id", HttpStatus.CONFLICT);
    }

    @PostMapping
    ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
        Message message = answerService.addAnswerToDatabase(answerDto);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
