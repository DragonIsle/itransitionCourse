package example.dao.impl;

import example.dao.CreativeDao;
import example.models.Creative;
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
            entityManager.remove(creative);
        }
    }
}
