package com.todo.ToDoList.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String id;

    @Size(min = 2, max = 30)
    private String name;
}