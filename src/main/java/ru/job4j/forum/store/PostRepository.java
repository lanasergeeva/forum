package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository
        extends CrudRepository<Post, Integer> {
    @Query("SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.replies r "
            + "ORDER BY p.id")
    List<Post> findAll();

    @Query("SELECT DISTINCT p FROM Post p "
            + "LEFT JOIN FETCH p.replies r "
            + " WHERE p.id = ?1")
    Optional<Post> findById(Integer integer);
}
