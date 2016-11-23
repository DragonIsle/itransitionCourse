package example.dao;

import example.models.User;

import java.util.Collection;

public interface UserDao {

    User getUser(String login);

    void add(User user);

    void remove(String login);

    void update(User user);

    Collection<User> getAll();
}
