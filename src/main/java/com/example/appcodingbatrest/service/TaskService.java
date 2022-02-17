package com.example.appcodingbatrest.service;

import com.example.appcodingbatrest.entities.Category;
import com.example.appcodingbatrest.entities.Example;
import com.example.appcodingbatrest.entities.Task;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.payload.TaskDto;
import com.example.appcodingbatrest.repository.CategoryRepository;
import com.example.appcodingbatrest.repository.ExampleRepository;
import com.example.appcodingbatrest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private ExampleRepository exampleRepository;

    /**
     * TO GET ALL TASKS FROM DATABASE;
     * @return
     */
    public List<Task> getAllTask(){
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }


    /**
     * TO GET TASK BY ID FROM DATABASE;
     */
    public Task getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    /**
     * TO POST NEW TASK TO DATABASE TO SAVE
     */
    public Message addTaskToDataBase(TaskDto taskDto){
        boolean existsByName = taskRepository.existsByName(taskDto.getName());
        if (existsByName){
            return new Message("Task  already exits", false);
        }

        Optional<Category> category = categoryRepository.findById(taskDto.getCategoryId());
        if (category.isEmpty()){
            return new Message("Category not found", false);
        }

        List<Example> allById = exampleRepository.findAllById(taskDto.getExample());

        Task task = new Task(
                null,
                taskDto.getName(),
                taskDto.getText(),
                taskDto.getSolution(),
                taskDto.getMethod(),
                taskDto.getStar(),
                category.get(),
                allById
        );
        taskRepository.save(task);
        return new Message("Task successFull added", true);
    }

    public Message deleteTask(Long id){
        try {
            taskRepository.deleteById(id);
            exampleService.deleteExample(id);
            return new Message("Success", true);
        }
        catch (Exception e){
            return new Message("Error", false);
        }
    }
}
