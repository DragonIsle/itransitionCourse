package example.service.impl;

import example.dao.UserDao;
import example.models.User;
import example.service.UserService;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void remove(String login) {
        userDao.remove(login);
    }
}
