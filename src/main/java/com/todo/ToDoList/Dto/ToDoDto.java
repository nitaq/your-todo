package com.todo.ToDoList.Dto;

import lombok.Data;

@Data
public class ToDoDto {

    private String title;
    private String description;
    private Boolean completed;
    private String user_id;
}
