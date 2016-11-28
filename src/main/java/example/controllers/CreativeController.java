package example.controllers;

import example.models.Chapter;
import example.models.Creative;
import example.models.Tag;
import example.service.ChapterService;
import example.service.CreativeService;
import example.service.TagService;
import example.service.UserService;
import example.session.MySessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

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

    @Autowired
    private ChapterService chs;

    @Resource(name = "session")
    private MySessionClass session;

    @RequestMapping(method = RequestMethod.POST)
    public Collection<Creative> getAll(){
        return cs.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Creative> getPersonalCreatives(){
        return us.getUser(session.getUser().getLogin()).getCreatives();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void remove(@RequestParam Integer creativeId){
        cs.remove(creativeId);
    }

    @RequestMapping(value = "single", method = RequestMethod.GET)
    public Creative getById(@RequestParam Integer creativeId){
        return cs.getById(creativeId);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public Collection<Tag> getTags(@RequestParam Integer creativeId){
        return ts.getByCreative(creativeId);
    }

    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    public Collection<Chapter> getChapters(@RequestParam Integer creativeId){
        Creative cr=cs.getById(creativeId);
        return cr.getChapters();
    }
    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    public String getCreator(@RequestParam Integer creativeId){
        Creative cr=cs.getById(creativeId);
        return cr.getAuthor().getName();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Collection<Chapter> addChapter(@RequestParam Integer creativeId){
        Creative cr=cs.getById(creativeId);
        Chapter ch=new Chapter();
        ch.setCreative(cr);
        ch.setNumber(cr.getChapters().size()+1);
        ch.setText("Hello, World!");
        chs.add(ch);
        return chs.getByCreativeId(cr.getId());
    }
    @RequestMapping(value="/chapter/remove", method = RequestMethod.DELETE)
    public Collection<Chapter> deleteChapter(@RequestParam Integer chapterId, @RequestParam Integer creativeId){
        chs.remove(chapterId);
        return chs.getByCreativeId(creativeId);
    }
    @RequestMapping(value="/chapter/save", method = RequestMethod.POST)
    public void saveChapter(@RequestBody Chapter chapter){
        chapter.setCreative(chs.getById(chapter.getId()).getCreative());
        chs.update(chapter);
    }
    @RequestMapping(value="/tag/add", method = RequestMethod.POST)
    public Collection<Tag> addTag(@RequestBody Tag tag, @RequestParam Integer creativeId){
        ts.addTag(tag, creativeId);
        return ts.getByCreative(creativeId);
    }
    @RequestMapping(value="/name/save", method = RequestMethod.GET)
    public Creative changeName(@RequestParam Integer creativeId, @RequestParam("name") String name){
        Creative cr=cs.getById(creativeId);
        cr.setName(name);
        cs.update(cr);
        return cs.getById(cr.getId());
    }
    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public void rate(@RequestParam Integer creativeId, @RequestParam Integer rating){
        Creative cr=cs.getById(creativeId);
        cr.setRating((cr.getRating()*cr.getRateCount()+rating)/(cr.getRateCount()+1));
        cr.setRateCount(cr.getRateCount()+1);
        cs.update(cr);
    }

    @RequestMapping(value="/tag/remove", method = RequestMethod.POST)
    public Collection<Tag> removeTag(@RequestBody Tag tag, @RequestParam Integer creativeId){
        ts.remove(tag, creativeId);
        return ts.getByCreative(creativeId);
    }

    @RequestMapping(value = "/get/bytag", method = RequestMethod.GET)
    public Collection<Creative> getByTag(@RequestParam("name") String name){
        return ts.get(name).getCreatives();
    }
    @RequestMapping(value = "/tag/all", method = RequestMethod.GET)
    public Collection<Tag> getAllTags(){
        Collection<Tag> tags=ts.getAll();
        Iterator<Tag> i=tags.iterator();
        while(i.hasNext()){
            Tag tg=i.next();
            if(tg.getCreatives().size()==0) {
                i.remove();
            }
        }
        return tags;
    }
}
