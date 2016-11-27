package example.dao;

import example.models.Creative;
import example.models.Tag;

import java.util.Collection;

/**
 * Created by saul on 11/22/16.
 */
public interface TagDao {
    Collection<Tag> getAll();
    Collection<Tag> getByCreative(int creativeId);
    void addTag(Tag tag, Integer creativeId);
    void remove(Tag tag, Integer creativeId);
    Tag get(String name);
}
