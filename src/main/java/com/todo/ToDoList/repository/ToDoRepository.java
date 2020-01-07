package com.todo.ToDoList.repository;

import com.todo.ToDoList.model.ToDoItem;
import com.todo.ToDoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoItem, Integer> {

    List<ToDoItem> findAllByUsers_Id(String id);
}