package com.example.appcodingbatrest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private String text;
    private Boolean correct;
    private Long taskId;
    private Long userId;
}
