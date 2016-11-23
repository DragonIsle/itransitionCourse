package example.dao.impl;

import example.dao.TagDao;
import example.models.Creative;
import example.models.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by saul on 11/22/16.
 */
@Repository
public class TagDaoImpl implements TagDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Tag> getAll() {
        return entityManager.createQuery("from Tag", Tag.class).getResultList();
    }

    public Collection<Tag> getByCreative(int creativeId){
        Creative cr=entityManager.find(Creative.class, creativeId);
        return cr.getTags();
    }
}
