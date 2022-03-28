package ru.job4j.forum.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMemory {
    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger userIndex = new AtomicInteger(1);


    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(int id) {
        return users.get(id);
    }

    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(userIndex.getAndIncrement());
        }
        users.put(user.getId(), user);
    }


    public User existUserByUsername(String name) {
        return users.values().stream()
                .filter(user -> name.equals(user.getUsername()))
                .findAny()
                .orElse(null);
    }
}
