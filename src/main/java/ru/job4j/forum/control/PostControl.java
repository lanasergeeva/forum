package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Reply;
import org.springframework.security.core.userdetails.User;
import ru.job4j.forum.services.PostServiceData;
import ru.job4j.forum.services.ReplyServices;
import ru.job4j.forum.services.UserServicesData;

import java.util.Calendar;

@Controller
public class PostControl {
    private final PostServiceData posts;
    private final ReplyServices replies;
    private final UserServicesData users;


    public PostControl(PostServiceData posts, ReplyServices replies, UserServicesData users) {
        this.posts = posts;
        this.replies = replies;
        this.users = users;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.findAll());
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

    @GetMapping("/edit")
    public String create(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        User user = (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(users.existUserByUsername(user.getUsername()));
        posts.save(post);
        return "redirect:/";
    }

    @GetMapping("/reply")
    public String createReply(@RequestParam("postId") int id, Model model) {
        Reply reply = new Reply();
        model.addAttribute("reply", reply);
        model.addAttribute("post", posts.findById(id));
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "reply";
    }


    @GetMapping("/post")
    public String post(@RequestParam("postId") int id, Model model) {
        model.addAttribute("post", posts.findById(id));
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "post";
    }


    @PostMapping("/savereply")
    public String saveReply(@ModelAttribute Reply reply,
                            @RequestParam("postId") int id) {
        Post byId = posts.findById(id);
        User user =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reply.setUser(users.existUserByUsername(user.getUsername()));
        reply.setDate(Calendar.getInstance());
        reply.setPost(byId);
        replies.saveReply(reply);
        return "redirect:/post?postId=" + byId.getId();
    }

    @GetMapping("/update")
    public String updatePost(@RequestParam("postId") int id, Model model) {
        Post post = posts.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "edit";
    }

    @GetMapping ("/delete")
    public String deletePost(@RequestParam("acId") int id) {
        posts.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/repdelete")
    public String deleteReply(@RequestParam("postId") int id,
                              @RequestParam("repId") int idRep) {
        replies.deleteReply(idRep);
        return "redirect:/post?postId=" + id;
    }
}
