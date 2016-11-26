package example.dao.impl;

import example.dao.CreativeDao;
import example.models.Chapter;
import example.models.Creative;
import example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by saul on 11/21/16.
 */
@Repository
public class CreativeDaoImpl implements CreativeDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void update(Creative creative) {
        Creative cr=entityManager.find(Creative.class, creative.getId());
        if(cr!=null) {
            cr.setName(creative.getName());
            cr.setRating(creative.getRating());
            cr.setRateCount(creative.getRateCount());
            entityManager.remove(cr);
            entityManager.persist(cr);
        }
    }

    @Override
    public Creative getById(int id) {
        return entityManager.find(Creative.class, id);
    }

    @Override
    public Collection<Creative> getByUserLogin(String login) {
        return entityManager.find(User.class, login).getCreatives();
    }

    @Override
    public Collection<Creative> getAll(){
        return entityManager.createQuery("from Creative", Creative.class).getResultList();
    }

    @Override
    public void add(Creative creative) {
        entityManager.persist(creative);
    }

    @Override
    public void remove(Integer id) {
        Creative creative = entityManager.find(Creative.class, id);
        if (null != creative) {
            for(Chapter chapter:creative.getChapters()){
                entityManager.remove(chapter);
            }
            entityManager.remove(creative);
        }
    }
}
