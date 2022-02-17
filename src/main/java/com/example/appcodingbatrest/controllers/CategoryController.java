package com.example.appcodingbatrest.controllers;

import com.example.appcodingbatrest.entities.Category;
import com.example.appcodingbatrest.payload.CategoryDto;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * TO GET ALL CATEGORIES FROM DATABASE
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        List<Category> allCategory = categoryService.getAllCategory();
        return allCategory != null
                ? new ResponseEntity<>(allCategory, HttpStatus.OK)
                : new ResponseEntity<>("Categories was not founded", HttpStatus.CONFLICT);
    }

    /**
     * TO GET ONLY ONE CATEGORY FROM DATABASE BY ID FROM CLIENT
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategories(@PathVariable Long id){
        Category category = categoryService.getCategory(id);
        return category!=null
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>("IS NOT FOUND", HttpStatus.CONTINUE);
    }

    /**
     * TO ADD CATEGORY TO DATABASE
     * @param categoryDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        Message message = categoryService.addCategory(categoryDto);
        return message.isSuccess()
                ? new ResponseEntity<>(message,HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    /**
     * TO UPDATE CATEGORY
     * @param id
     * @param categoryDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editCategoryById(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        Message message = categoryService.addCategory(categoryDto);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.ACCEPTED)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }


    /**
     * TO
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable long id){
        Message message = categoryService.deleteCategory(id);
        return message.isSuccess()
                ? new ResponseEntity<>(message,HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
