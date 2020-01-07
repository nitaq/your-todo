package com.todo.ToDoList.service;

import com.todo.ToDoList.model.User;
import com.todo.ToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> result = userRepository.findById(id);

        User user = null;

        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    private void saveById(String id) {
        Optional<User> result = userRepository.findById(id);

        if(!result.isPresent()) {
            User user = new User();
            user.setId(id);
            userRepository.save(user);
        }else{
            throw new RuntimeException("User already exists");
        }
    }

    public void createUserWithId(String id) {
        if (id == null) {
            throw new RuntimeException("User without an id cannot be inserted!");
        } else {
           saveById(id);
        }
    }
}
