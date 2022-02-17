package com.example.appcodingbatrest.payload;

import lombok.Data;

import java.util.List;

@Data
public class TaskDto {
    private String name;
    private String method;
    private String solution;
    private Boolean star;
    private String text;
    private Long categoryId;
    private List<Long> example;
}
