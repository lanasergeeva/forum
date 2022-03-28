package ru.job4j.forum.control.memory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;

import ru.job4j.forum.service.AuthService;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private final UserService users;
    private final AuthService authorities;

    public RegControl(UserService users,
                      AuthService authorities) {
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (users.existUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMessage", "Логин занят!");
            return regPage();
        }
        user.setAuthority(authorities.existAuthorityByAuthority("ROLE_USER"));
        users.save(user);
        System.out.println(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
