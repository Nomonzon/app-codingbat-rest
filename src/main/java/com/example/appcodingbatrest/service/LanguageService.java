package com.example.appcodingbatrest.service;

import com.example.appcodingbatrest.entities.Language;
import com.example.appcodingbatrest.entities.User;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.repository.LanguageRepository;
import com.example.appcodingbatrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    /**
     * TO GET Language LIST FROM DATABASE
     * @return LANGUAGE LIST
     */
    public List<Language>  getLanguages(){
        return languageRepository.findAll();
    }

    /**
     * TO GET LANGUAGE BY ID FROM DATABASE
     * @param id LONG
     * @return USER
     */
    public Language getLanguageById(Long id){
        return languageRepository.findById(id).orElse(null);
    }

    /**
     * TO ADD LANGUAGE TO DATABASE
     * IF USER WAS EXITED EV
     */
    public Message addLanguage(Language language){
        boolean existsByName =
                languageRepository.existsByName(language.getName());

        if (existsByName){
            return new Message("This language already exists.", false);
        }

        languageRepository.save(language);
        return new Message("Success language is added.", true);
    }

    /**
     * TO EDIT LANGUAGE
     * @param language
     * @param id
     * @return
     */

    public Message editLanguage(Language language, Long id){
        boolean nameAndIdNot = languageRepository.existsByNameAndIdNot(language.getName(), id);
        if (nameAndIdNot){
            return new Message("This name already exists.", false);
        }
        Optional<Language> languageOptional = languageRepository.findById(id);
        if (languageOptional.isEmpty()){
            return new Message("This name not found by id.", false);
        }
        Language editedLanguage = languageOptional.get();
        editedLanguage.setName(language.getName());
        return new Message("Success edited.", true);
    }

    /**
     * TO DELTE LANGUAGE
     * @param id
     * @return
     */
    public Message deleteLanguage(Long id){
        try {
            languageRepository.deleteById(id);
            return new Message("Success", true);
        }
        catch (Exception e){
            return  new Message("Error", false);
        }
    }
}
