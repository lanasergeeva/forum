package ru.job4j.forum.control.memory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;


@Controller
public class LoginControl {
    private final UserService users;

    public LoginControl(UserService users) {
        this.users = users;
    }

    @RequestMapping("/login")
    public String loginPage(@ModelAttribute User user, Model model) {
        User userByUsername = users.existUserByUsername(user.getUsername());
        if (userByUsername == null) {
            model.addAttribute("errorMessage",
                    "Такого пользователя не существует!");
            return logPage();
        } else if (!userByUsername.getPassword().equals(user.getPassword())) {
            model.addAttribute("errorMessage",
                    "Вы ввели неверный пароль!");
            return logPage();
        }
        System.out.println(userByUsername);
        return "redirect:/index?name=" + userByUsername.getUsername();
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String logPage() {
        return "login";
    }
}

