package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;


import java.util.List;

@Service
public class PostServiceData {

    private final PostRepository posts;

    public PostServiceData(PostRepository posts) {
        this.posts = posts;
    }

    public List<Post> findAll() {
        return posts.findAll();
    }

    public void save(Post post) {
        if (post.getId() > 0) {
            Post byId = findById(post.getId());
            post.setReplies(byId.getReplies());
            post.setCreated(byId.getCreated());
        }
        posts.save(post);
    }

    public Post findById(int id) {
        return posts.findById(id).get();
    }

    public void delete(int id) {
        posts.delete(findById(id));
    }
}
