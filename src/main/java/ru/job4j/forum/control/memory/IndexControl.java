package ru.job4j.forum.control.memory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Reply;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class IndexControl {

    private final PostService posts;
    private final UserService users;

    private boolean flag = false;
    private List<String> usersList = new ArrayList<>();

    public IndexControl(PostService posts, UserService users) {
        this.posts = posts;
        this.users = users;
    }

    /**
     * Примитивная реализация для хранения авторизованного пользователя.
     * Он добавляется в лист 1 раз.
     * Также здесь сохраняю сразу пользователя из дефолтных постов.
     *
     * @param name имя авторизованного пользователя
     */
    private void setUserNameInSession(String name) {
        if (!flag) {
            flag = true;
            usersList.add(name);
            if (posts.findAll().size() > 0) {
                users.save(posts.findAll().get(0).getUser());
            }
        }
    }

    /**
     * @return имя авторизованного пользователя, если его нет - дефолтного
     * пользователя c именем "lana"
     */
    private String getUserName() {
        String rsl = "lana";
        if (usersList.size() > 0) {
            rsl = usersList.get(0);
        }
        return rsl;
    }

    @RequestMapping({"/", "/index"})
    public String index(@RequestParam(defaultValue = "check") String name, Model model) {
        setUserNameInSession(name);
        if (name.equals("check")) {
            name = getUserName();
        }
        model.addAttribute("user", users.existUserByUsername(name));
        model.addAttribute("posts", posts.findAll());
        model.addAttribute("userInSession", getUserName());
        return "index";
    }

    @GetMapping("/edit")
    public String create(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        model.addAttribute("userName", getUserName());
        System.out.println(usersList);
        return "edit";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Post post) {
        User userByUsername = users.existUserByUsername(getUserName());
        post.setUser(userByUsername);
        posts.save(post);
        return "redirect:/";
    }

    @RequestMapping("/reply")
    public String createReply(@RequestParam("postId") int id, Model model) {
        Post post = posts.findById(id);
        Reply reply = new Reply();
        model.addAttribute("reply", reply);
        model.addAttribute("post", post);
        model.addAttribute("userName", getUserName());
        return "reply";
    }


    @GetMapping("/post")
    public String post(@RequestParam("postId") int id, Model model) {
        Post byId = posts.findById(id);
        model.addAttribute("post", byId);
        model.addAttribute("userName", getUserName());
        return "post";
    }


    @RequestMapping("/savereply")
    public String saveReply(@ModelAttribute Reply reply,
                            @RequestParam("postId") int id) {
        Post byId = posts.findById(id);
        System.out.println("savereply пост " + byId);
        User userByUsername = users.existUserByUsername(getUserName());
        User author = byId.getUser();
        if (!author.equals(userByUsername)) {
            author = userByUsername;
        }
        reply.setUser(author);
        reply.setDate(Calendar.getInstance());
        posts.addReplyMem(byId, reply);
        return "redirect:/post?postId=" + byId.getId();
    }

    @RequestMapping("/update")
    public String updatePost(@RequestParam("postId") int id, Model model) {
        Post post = posts.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("userName", getUserName());
        return "edit";
    }

    @RequestMapping("/delete")
    public String deletePost(@RequestParam("acId") int id) {
        posts.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/repdelete")
    public String deleteReply(@RequestParam("postId") int id,
                              @RequestParam("repId") int idRep
    ) {
        posts.deleteReplyMem(id, idRep);
        return "redirect:/post?postId=" + id;
    }
}
