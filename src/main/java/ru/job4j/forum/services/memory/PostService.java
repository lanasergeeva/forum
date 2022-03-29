package ru.job4j.forum.services.memory;

import ru.job4j.forum.store.memory.PostMemory;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Reply;

import java.util.List;

/*@Service*/
public class PostService {
    private final PostMemory postMemory;

    public PostService(PostMemory postMemory) {
        this.postMemory = postMemory;
    }

    public List<Post> findAll() {
        return postMemory.findAll();
    }

    public void save(Post post) {
        postMemory.save(post);
    }

    public Post findById(int id) {
        return postMemory.findById(id);
    }

    public void delete(int id) {
        postMemory.delete(id);
    }

    public void addReplyMem(Post post, Reply reply) {
        postMemory.addReplyMem(post, reply);
    }

    public void deleteReplyMem(int postId, int repId) {
        postMemory.deleteReplyMem(postId, repId);
    }

}
