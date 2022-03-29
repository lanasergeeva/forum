package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Reply;
import ru.job4j.forum.store.ReplyRepository;

@Service
public class ReplyServices {

    private final ReplyRepository replies;

    public ReplyServices(ReplyRepository replies) {
        this.replies = replies;
    }

    public void saveReply(Reply reply) {
        replies.save(reply);
    }

    public void deleteReply(int id) {
        Reply reply = replies.findById(id).get();
        replies.delete(reply);
    }
}
