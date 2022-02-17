package com.example.appcodingbatrest.controllers;

import com.example.appcodingbatrest.entities.Task;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.payload.TaskDto;
import com.example.appcodingbatrest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    /**
     * TO GET ALL TASKS FROM DATABASE
     * @return TASK ALL
     */
    @GetMapping
    public ResponseEntity<?> getAllTask(){
        List<Task> allTask = taskService.getAllTask();
        return allTask != null && allTask.isEmpty()
                ? new ResponseEntity<>(allTask,HttpStatus.OK)
                : new ResponseEntity<>("Table task is empty", HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        Task taskById = taskService.getTaskById(id);
        return taskById != null
                ? new ResponseEntity<>(taskById, HttpStatus.OK)
                : new ResponseEntity<>("Task not found", HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskDto taskDto){
        Message message = taskService.addTaskToDataBase(taskDto);
        return message.isSuccess()
                ? new ResponseEntity<>(message ,HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        Message message = taskService.deleteTask(id);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
