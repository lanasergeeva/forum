package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Reply;

public interface ReplyRepository
        extends CrudRepository<Reply, Integer> {
}
