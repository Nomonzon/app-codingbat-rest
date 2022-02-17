package com.example.appcodingbatrest.service;

import com.example.appcodingbatrest.entities.Category;
import com.example.appcodingbatrest.entities.Language;
import com.example.appcodingbatrest.payload.CategoryDto;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.repository.CategoryRepository;
import com.example.appcodingbatrest.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }


    public Category getCategory(Long id){return categoryRepository.findById(id).orElse(null);
    }

    public Message addCategory(CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName){
            return new Message("Category  already exists by this name",false);
        }
        Optional<Language> languageOptional = languageRepository.findById(categoryDto.getLanguageId());
        if (languageOptional.isEmpty()){
            return new Message("Language by this id not found", false);
        }

        Category category = new Category(
                null,
                categoryDto.getName(),
                categoryDto.getDescription(),
                languageOptional.get()
        );

        categoryRepository.save(category);

        return new Message("Success! Category is added" ,true);
    }

    public Message editCategory(CategoryDto categoryDto, Long id){

        boolean nameAndIdNot = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (nameAndIdNot){
            return new Message("Category already added by another id",false);
        }

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()){
            return new Message("Category already is not found by this id", false);
        }

        Optional<Language> languageOptional = languageRepository.findById(id);
        if (languageOptional.isEmpty()){
            return  new Message("Language is not found", false);
        }

        Category category = categoryOptional.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(languageOptional.get());
        categoryRepository.save(category);
        return new Message("Success edited", true);
    }

    public Message deleteCategory(Long id){
        try {
            categoryRepository.deleteById(id);
            return new Message("Success deleted", true);
        }
        catch (Exception e){
            return new Message("Error!", false);
        }
    }
}
