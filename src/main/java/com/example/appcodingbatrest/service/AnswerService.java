package com.example.appcodingbatrest.service;

import com.example.appcodingbatrest.entities.Answer;
import com.example.appcodingbatrest.entities.Task;
import com.example.appcodingbatrest.entities.User;
import com.example.appcodingbatrest.payload.AnswerDto;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;


    /**
     * TO GET ALL ANSWER FROM DATABASE
     */
    public List<Answer> getAllAnswersFromDataBase(){
        return answerRepository.findAll();
    }

    /**
     * TO GET ANSWER FROM DATABASE BY ID
     */

    public Answer getAnswerById(Long id){
        return answerRepository.findById(id).orElse(null);
    }

    /**
     * TO ADD ANSWER TO DATABASE
     * @param answerDto
     * @return "MESSAGE"
     */
    public Message addAnswerToDatabase(AnswerDto answerDto){

        User userById = userService.getUserById(answerDto.getUserId());

        Task taskById = taskService.getTaskById(answerDto.getTaskId());
        if (userById == null){
            return new Message("User not found. Please sign up before solving answer", false);
        }


        Answer answer = new Answer(
                null,
                answerDto.getText(),
                answerDto.getCorrect(),
                userById,
                taskById
         );
        answerRepository.save(answer);
        return new Message("Answer saved", true);
    }

}
