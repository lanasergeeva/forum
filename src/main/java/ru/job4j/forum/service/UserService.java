package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.memory.UserMemory;
import ru.job4j.forum.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserMemory userMemory;

    public UserService(UserMemory userMemory) {
        this.userMemory = userMemory;
    }

    public List<User> findAll() {
        return userMemory.findAll();
    }

    public User findById(int id) {
        return userMemory.findById(id);
    }

    public void save(User user) {
        userMemory.save(user);
    }

    public User existUserByUsername(String name) {
        return userMemory.existUserByUsername(name);
    }
}
