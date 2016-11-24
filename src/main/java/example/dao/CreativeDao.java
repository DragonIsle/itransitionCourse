package example.dao;

import example.models.Creative;
import example.models.User;

import java.util.Collection;

/**
 * Created by saul on 11/21/16.
 */
public interface CreativeDao {
    Collection<Creative> getAll();

    void add(Creative creative);

    void remove(Integer id);

    Collection<Creative> getByUserLogin(String login);

    Creative getById(int id);
}
