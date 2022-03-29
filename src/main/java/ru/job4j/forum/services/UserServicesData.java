package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.UserRepository;

import java.util.List;

@Service
public class UserServicesData {

    private final UserRepository users;

    public UserServicesData(UserRepository users) {
        this.users = users;
    }

    public List<User> findAll() {
        return (List<User>) users.findAll();
    }

    public User findById(int id) {
        return users.findById(id).get();
    }

    public void save(User user) {
        users.save(user);
    }

    public User existUserByUsername(String name) {
        return users.findUserByUsername(name);
    }

    public boolean existsUserByUsername(String name) {
        return users.existsByUsername(name);
    }
}
