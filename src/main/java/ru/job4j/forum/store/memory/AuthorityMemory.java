package ru.job4j.forum.store.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorityMemory {
    private final Map<Integer, Authority> authorities = new HashMap<>();
    private final AtomicInteger authIndex = new AtomicInteger(1);

    public List<Authority> findAll() {
        System.out.println(authorities);
        return new ArrayList<>(authorities.values());
    }

    public Authority findById(int id) {
        return authorities.get(id);
    }

    public void save(Authority authority) {
        if (authority.getId() == 0) {
            authority.setId(authIndex.getAndIncrement());
        }
        authorities.put(authority.getId(), authority);
    }

    public Authority existAuthorityByName(String name) {
        return authorities.values().stream()
                .filter(a -> name.equals(a.getName()))
                .findAny()
                .orElse(null);
    }
}
