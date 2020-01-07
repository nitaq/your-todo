package com.todo.ToDoList.Dto;

import lombok.Data;

@Data
public class EditToDoDto {

    private int id;
    private String title;
    private String description;
    private Boolean completed;
    private String user_id;
}