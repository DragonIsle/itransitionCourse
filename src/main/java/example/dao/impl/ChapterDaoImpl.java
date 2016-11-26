package example.dao.impl;

import example.dao.ChapterDao;
import example.models.Chapter;
import example.models.Creative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by saul on 11/25/16.
 */
@Repository
public class ChapterDaoImpl implements ChapterDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void remove(Integer id) {
        Chapter chapter = entityManager.find(Chapter.class, id);
        if (null != chapter) {
            entityManager.remove(chapter);
        }
    }

    @Override
    public Chapter getById(Integer id) {
        return entityManager.find(Chapter.class, id);
    }

    @Override
    public Collection<Chapter> getByCreativeId(Integer creativeId) {
        return entityManager.find(Creative.class, creativeId).getChapters();
    }

    @Override
    public void add(Chapter chapter) {
        entityManager.persist(chapter);
    }

    @Override
    public void update(Chapter ch) {
        Chapter chapter=entityManager.find(Chapter.class, ch.getId());
        chapter.setText(ch.getText());
        entityManager.remove(chapter);
        entityManager.persist(chapter);
    }
}
