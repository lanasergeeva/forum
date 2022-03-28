package ru.job4j.forum.model;

import javax.persistence.*;
import java.util.Objects;

/*@Entity
@Table(name = "users")*/
public class User {
    /*    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private int id;

    private String password;

    private String username;

    /* @ManyToOne
     @JoinColumn(name = "authority_id")*/
    private Authority authority;

    private boolean enabled;

    public static User of(String username, String password, Authority authority) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(true);
        user.setAuthority(authority);
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", password='" + password + '\''
                + ", username='" + username + '\''
                + ", authority=" + authority
                + ", enabled=" + enabled
                + '}';
    }
}
