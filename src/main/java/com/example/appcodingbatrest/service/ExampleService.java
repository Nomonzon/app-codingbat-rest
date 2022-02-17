package com.example.appcodingbatrest.service;

import com.example.appcodingbatrest.entities.Example;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.payload.TaskDto;
import com.example.appcodingbatrest.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    public Message addExample(Example example){
        exampleRepository.save(example);
        return new Message("Success added", true);
    }

   public Message deleteExample(Long id){
        try {
            exampleRepository.deleteById(id);
         return new Message("Success", true);
        }
        catch (Exception e){
            return new Message("Error example not found", false);
        }
   }

}
