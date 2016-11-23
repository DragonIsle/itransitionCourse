package example.service;

import example.models.Creative;
import example.models.Tag;

import java.util.Collection;

/**
 * Created by saul on 11/22/16.
 */
public interface TagService {
    Collection<Tag> getAll();
    Collection<Tag> getByCreative(int creativeId);
}