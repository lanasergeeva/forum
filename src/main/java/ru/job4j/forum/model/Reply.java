package ru.job4j.forum.model;

import java.util.Calendar;
import java.util.Objects;

public class Reply {
    private int id;
    private String text;
    private Calendar date;
    private User user;

    public static Reply of(String text, User user) {
        Reply reply = new Reply();
        reply.setText(text);
        reply.setDate(Calendar.getInstance());
        reply.setUser(user);
        return reply;
    }

    public Reply(User user) {
        this.user = user;
    }

    public Reply() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reply reply = (Reply) o;
        return id == reply.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reply{" + "id=" + id
                + ", text='" + text + '\''
                + ", date=" + date
                + ", user=" + user
                + '}';
    }
}
