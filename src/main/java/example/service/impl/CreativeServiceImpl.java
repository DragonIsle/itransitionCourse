package example.service.impl;

import example.dao.CreativeDao;
import example.models.Creative;
import example.service.CreativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by saul on 11/21/16.
 */
@Transactional
@Service
public class CreativeServiceImpl implements CreativeService {
    @Autowired
    private CreativeDao creativeDao;

    @Override
    public Collection<Creative> getAll() {
        return creativeDao.getAll();
    }

    @Override
    public void remove(Integer id) {
        creativeDao.remove(id);
    }

    @Override
    public void add(Creative creative) {
        creativeDao.add(creative);
    }
}
