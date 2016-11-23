package example.dao.impl;

import example.dao.UserDao;
import example.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(String login) {
        return entityManager.find(User.class, login);
    }

    @Override
    public void update(User user) {
        entityManager.refresh(user);
    }

    @Override
    public void remove(String login) {
        User user = entityManager.find(User.class, login);
        if (null != user) {
            entityManager.remove(user);
        }
    }

    @Override
    public Collection<User> getAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}
