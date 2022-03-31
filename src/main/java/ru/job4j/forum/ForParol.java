package ru.job4j.forum;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ForParol {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("111");
        System.out.println(pwd);
    }
}
