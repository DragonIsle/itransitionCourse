package example.service;

import example.models.Chapter;

import java.util.Collection;

/**
 * Created by saul on 11/25/16.
 */
public interface ChapterService {
    void add(Chapter chapter);
    Collection<Chapter> getByCreativeId(Integer creativeId);
    Chapter getById(Integer id);
    void remove(Integer id);
    void update(Chapter ch);
}
