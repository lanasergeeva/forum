package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.services.AuthServicesData;
import ru.job4j.forum.services.UserServicesData;

@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserServicesData users;
    private final AuthServicesData authorities;

    public RegControl(PasswordEncoder encoder,
                      UserServicesData users, AuthServicesData authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (users.existsUserByUsername(user.getUsername())) {
            model.addAttribute("errorMessage", "Логин занят!");
            return regPage();
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.existAuthorityByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
