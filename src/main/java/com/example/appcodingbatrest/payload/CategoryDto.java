package com.example.appcodingbatrest.payload;

import com.example.appcodingbatrest.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;

    private String description;

    private Long languageId;
}
