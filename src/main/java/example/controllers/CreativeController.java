package example.controllers;

import example.dao.CreativeDao;
import example.models.Chapter;
import example.models.Creative;
import example.models.Tag;
import example.service.CreativeService;
import example.service.TagService;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by saul on 11/14/16.
 */
@RestController
@RequestMapping("creative")
public class CreativeController {

    @Autowired
    private CreativeService cs;

    @Autowired
    private UserService us;

    @Autowired
    private TagService ts;

    @Resource(name = "session")
    private ConcurrentHashMap session;

    @RequestMapping(method = RequestMethod.POST)
    public Collection<Creative> getAll(){
        return cs.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Creative> getPersonalCreatives(@RequestParam String login){
        return us.getUser(login).getCreatives();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void remove(@RequestParam Integer creativeId){
        cs.remove(creativeId);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public Collection<Tag> getTags(@RequestParam Integer creativeId){
        session.put(1, 2);
        return ts.getByCreative(creativeId);
    }

    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    public Collection<Chapter> getChapters(){
        ArrayList<Chapter> chapters=new ArrayList<>();
        Chapter chapter=new Chapter();
        chapter.setNumber(1);
        chapter.setFileRef("/home/saul/fakeChapterFile");
        chapters.add(chapter);
        Chapter chapter1=new Chapter();
        chapter1.setNumber(2);
        chapter1.setFileRef("/home/saul/fakeChapterFile2");
        chapters.add(chapter1);
        return chapters;
    }
}
