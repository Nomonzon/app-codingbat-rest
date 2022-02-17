package com.example.appcodingbatrest.controllers;

import com.example.appcodingbatrest.entities.Language;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<?> getAllLanguages(){
        List<Language> languages = languageService.getLanguages();
        return languages !=null
                ? new ResponseEntity<>(languages, HttpStatus.OK)
                : new ResponseEntity<>("Languages was not found", HttpStatus.CONFLICT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguageById(@PathVariable Long id){
        Language languageById = languageService.getLanguageById(id);
        return languageById != null
                ? new ResponseEntity<>(languageById, HttpStatus.OK)
                : new ResponseEntity<>("Language is not found", HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<Message> addLanguage(@RequestBody Language language){
        Message message = languageService.addLanguage(language);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editLanguage(@RequestBody Language language, @PathVariable Long id){
        Message message = languageService.editLanguage(language, id);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Long id) {
        Message message = languageService.deleteLanguage(id);
        return message.isSuccess()
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
