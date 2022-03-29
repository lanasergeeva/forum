package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.store.AuthorityRepository;

@Service
public class AuthServicesData {
    private final AuthorityRepository authorities;

    public AuthServicesData(AuthorityRepository authorities) {
        this.authorities = authorities;
    }

    public Authority findById(int id) {
        return authorities.findById(id).get();
    }

    public void save(Authority authority) {
        authorities.save(authority);
    }

    public Authority existAuthorityByAuthority(String name) {

        return authorities.findAuthorityByName(name);
    }
}

