package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.memory.AuthorityMemory;
import ru.job4j.forum.model.Authority;

@Service
public class AuthService {
    private final AuthorityMemory authorityMemory;

    public AuthService(AuthorityMemory authorityMemory) {

        this.authorityMemory = authorityMemory;
    }

    public Authority findById(int id) {
        return authorityMemory.findById(id);
    }

    public void save(Authority authority) {
        authorityMemory.save(authority);
    }

    public Authority existAuthorityByAuthority(String name) {

        return authorityMemory.existAuthorityByName(name);
    }
}
