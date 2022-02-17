package com.example.appcodingbatrest.controllers;

import com.example.appcodingbatrest.entities.Example;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.payload.TaskDto;
import com.example.appcodingbatrest.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @PostMapping
    public ResponseEntity<?> addExample(@RequestBody Example example){
        Message message = exampleService.addExample(example);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
