package ru.job4j.forum.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private Calendar created;
    private User user;
    private List<Reply> replies;

    public static Post of(String name, String description, User user) {
        Post post = new Post();
        post.name = name;
        post.description = description;
        post.setCreated(Calendar.getInstance());
        post.setUser(user);
        return post;
    }

    public Post() {
    }

    public Post(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Post(int id, String name, String description, Calendar created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Post(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = Calendar.getInstance();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void addReply(Reply reply) {
        if (replies == null) {
            replies = new ArrayList<>();
        }
        replies.add(reply);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", user=" + user
                + ", replies=" + replies
                + '}';
    }
}
