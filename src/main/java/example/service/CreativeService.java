package example.service;

import example.models.Creative;

import java.util.Collection;

/**
 * Created by saul on 11/21/16.
 */
public interface CreativeService {
    Collection<Creative> getAll();
    void add(Creative creative);
    void remove(Integer id);
    Collection<Creative> getByUserLogin(String login);
    Creative getById(int id);
}
