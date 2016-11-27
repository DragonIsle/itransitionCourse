package example.dao.impl;

import example.dao.UserDao;
import example.models.Achievement;
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
        User u= entityManager.find(User.class, login);
        Achievement ach=entityManager.find(Achievement.class, "Respectable admin");
        if(u.getRole().equals("ADMIN")&&!u.getAchievements().contains(ach)){
            u.getAchievements().add(ach);
            entityManager.remove(u);
            entityManager.persist(u);
        }
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
