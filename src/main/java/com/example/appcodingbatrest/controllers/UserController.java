package com.example.appcodingbatrest.controllers;

import com.example.appcodingbatrest.entities.User;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers(){
        List<User> users = userService.getUsers();
        return users != null && users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>("Users was not found", HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        User userById = userService.getUserById(id);
        return userById != null
                ? new ResponseEntity<>(userById, HttpStatus.OK)
                : new ResponseEntity<>("User not found by this id", HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<Message> addUser(@RequestBody User user){
        Message message = userService.addUser(user);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> editUser(@RequestBody User user, @PathVariable Long id){
        Message message = userService.editUser(user, id);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteUserById(@PathVariable Long id){
        Message message = userService.deleteUser(id);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}

