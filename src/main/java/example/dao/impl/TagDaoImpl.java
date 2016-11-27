package example.dao.impl;

import example.dao.TagDao;
import example.models.Creative;
import example.models.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.LinkedList;

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

    @Override
    public void addTag(Tag tag, Integer creativeId){
        Creative cr=entityManager.find(Creative.class, creativeId);
        Tag tg=entityManager.find(Tag.class, tag.getName());
        if(tg==null) {
            cr.getTags().add(tag);
            entityManager.remove(cr);
            entityManager.persist(cr);
        }else{
            tg.getCreatives().add(cr);
            entityManager.remove(tg);
            entityManager.persist(tg);
        }
    }

    public Collection<Tag> getByCreative(int creativeId){
        Creative cr=entityManager.find(Creative.class, creativeId);
        if(cr.getTags().size()==0){
            return new LinkedList<Tag>();
        }
        return cr.getTags();
    }

    @Override
    public Tag get(String name) {
        return entityManager.find(Tag.class, name);
    }

    @Override
    public void remove(Tag tag, Integer creativeId) {
        Tag tag1 = entityManager.find(Tag.class, tag.getName());
        Creative cr=entityManager.find(Creative.class, creativeId);
        if(tag1.getCreatives().contains(cr)){
            tag1.getCreatives().remove(cr);
        }
        entityManager.remove(tag1);
        entityManager.persist(tag1);
    }
}
