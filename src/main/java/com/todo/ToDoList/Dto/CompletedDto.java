package com.todo.ToDoList.Dto;

import lombok.Data;

@Data
public class CompletedDto {

    private int id;
    private String title;
    private Boolean completed;
}
