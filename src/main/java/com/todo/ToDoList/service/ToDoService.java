package com.todo.ToDoList.service;

import com.todo.ToDoList.Dto.CompletedDto;
import com.todo.ToDoList.Dto.EditToDoDto;
import com.todo.ToDoList.Dto.ToDoDto;
import com.todo.ToDoList.model.ToDoItem;
import com.todo.ToDoList.model.User;
import com.todo.ToDoList.repository.ToDoRepository;
import com.todo.ToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;
    private UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public List<ToDoItem> findAll() {
        return toDoRepository.findAll();
    }

    public void save(ToDoItem toDoItem) {
        toDoRepository.save(toDoItem);
    }

    public ToDoItem findById(int id) {
        Optional<ToDoItem> result = toDoRepository.findById(id);

        //     return result.orElse(null);
        ToDoItem toDoItem = null;

        if (result.isPresent()) {
            toDoItem = result.get();
        } else {
            throw new RuntimeException("ToDo not found");
        }
        return toDoItem;
    }

    public void deleteById(int id) {
        toDoRepository.deleteById(id);
    }


    public void saveDto(ToDoDto toDoDto) {

        Optional<User> user = userRepository.findById(toDoDto.getUser_id());
        ToDoItem toDoItem = new ToDoItem();

        if (user.isPresent()) {
            toDoItem.setUsers(user.get());
        } else {
            throw new RuntimeException("User not found");
        }

        toDoItem.setTitle(toDoDto.getTitle());
        toDoItem.setCompleted(toDoDto.getCompleted());

        toDoRepository.save(toDoItem);
    }

    public void saveEditedDto(EditToDoDto theEditedToDoDto) {
        Optional<User> user = userRepository.findById(theEditedToDoDto.getUser_id());

        ToDoItem editToDo = new ToDoItem();

        if (user.isPresent()) {
            editToDo.setUsers(user.get());
        } else {
            throw new RuntimeException("User not found");
        }

        editToDo.setId(theEditedToDoDto.getId());
        editToDo.setTitle(theEditedToDoDto.getTitle());
        editToDo.setCompleted(theEditedToDoDto.getCompleted());

        toDoRepository.save(editToDo);
    }


    public List<ToDoItem> getTodos(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return toDoRepository.findAllByUsers_Id(id);

        } else {
            throw new RuntimeException("User not present.");
        }
    }

    public ToDoItem changeStatus(CompletedDto completedDto){
        Optional<ToDoItem> toDoItem = toDoRepository.findById(completedDto.getId());

        if(toDoItem.isPresent()){
            toDoItem.get().setTitle(completedDto.getTitle());
            toDoItem.get().setCompleted(completedDto.getCompleted());
            toDoRepository.save(toDoItem.get());
            return toDoItem.get();
        }else{
            throw new RuntimeException("ToDo not present");
        }

    }
}