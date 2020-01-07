package com.todo.ToDoList.controller;

import com.todo.ToDoList.Dto.CompletedDto;
import com.todo.ToDoList.Dto.EditToDoDto;
import com.todo.ToDoList.Dto.ToDoDto;
import com.todo.ToDoList.model.ToDoItem;
import com.todo.ToDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public ResponseEntity<List<ToDoItem>> findAllToDos() {
        List<ToDoItem> toDoItems = toDoService.findAll();
        return ResponseEntity.ok().body(toDoItems);
    }

    @PostMapping
    public ResponseEntity<ToDoItem> createToDo(@RequestBody ToDoItem toDoItem) {
        toDoService.save(toDoItem);
        return ResponseEntity.ok().body(toDoItem);
    }

    @PutMapping
    public ResponseEntity<ToDoItem> updateToDo(@RequestBody ToDoItem toDoItem) {
        toDoService.save(toDoItem);
        return ResponseEntity.ok().body(toDoItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getToDoItem(@PathVariable int id) {
        ToDoItem toDoItem = toDoService.findById(id);
        return ResponseEntity.ok().body(toDoItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable int id) {
        toDoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dto")
    public ResponseEntity<ToDoDto> createToDoDto(@RequestBody ToDoDto toDoDto) {
        toDoService.saveDto(toDoDto);
        return ResponseEntity.ok().body(toDoDto);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<ToDoItem>> findAllToDosByUser(@PathVariable String id) {
        List<ToDoItem> toDoItems = toDoService.getTodos(id);
        return ResponseEntity.ok().body(toDoItems);
    }

    @PutMapping("/editDto")
    public ResponseEntity<EditToDoDto> updateBooking(@RequestBody EditToDoDto editToDoDto) {
        toDoService.saveEditedDto(editToDoDto);
        return ResponseEntity.ok().body(editToDoDto);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<CompletedDto> changeStatus(@RequestBody CompletedDto completedDto) {
        toDoService.changeStatus(completedDto);
        return ResponseEntity.ok().body(completedDto);
    }

}