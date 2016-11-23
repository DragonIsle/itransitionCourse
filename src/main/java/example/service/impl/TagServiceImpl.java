package example.service.impl;

import example.dao.TagDao;
import example.models.Creative;
import example.models.Tag;
import example.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by saul on 11/22/16.
 */
@Transactional
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao td;

    @Override
    public Collection<Tag> getAll() {
        return td.getAll();
    }

    @Override
    public Collection<Tag> getByCreative(int creativeId) {
        return td.getByCreative(creativeId);
    }
}
