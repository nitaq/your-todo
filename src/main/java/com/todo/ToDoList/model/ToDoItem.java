package com.todo.ToDoList.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "to_do_item")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private boolean completed;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User users;
}