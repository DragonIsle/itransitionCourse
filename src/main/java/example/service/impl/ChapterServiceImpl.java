package example.service.impl;

import example.dao.ChapterDao;
import example.dao.CreativeDao;
import example.models.Chapter;
import example.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by saul on 11/25/16.
 */
@Transactional
@Service
public class ChapterServiceImpl implements ChapterService{
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public void remove(Integer id) {
        chapterDao.remove(id);
    }

    @Override
    public Chapter getById(Integer id) {
        return chapterDao.getById(id);
    }

    @Override
    public void update(Chapter ch) {
        chapterDao.update(ch);
    }

    @Override
    public Collection<Chapter> getByCreativeId(Integer creativeId) {
        return chapterDao.getByCreativeId(creativeId);
    }

    @Override
    public void add(Chapter chapter) {
        chapterDao.add(chapter);
    }
}
