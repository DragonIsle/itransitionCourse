package example.controllers;

import example.models.Chapter;
import example.models.Creative;
import example.models.User;
import example.service.CreativeService;
import example.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService us;

    @Autowired
    private CreativeService cs;

    @RequestMapping(method = RequestMethod.POST)
    public User authorize(@RequestBody User user) {
        User u=us.getUser(user.getLogin());
        if(u!=null&&u.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))){
            return u;
        }
        return null;
    }
    @RequestMapping(method=RequestMethod.DELETE)
    public Collection<User> delete(@RequestParam("login") String s){
        us.remove(s);
        return us.getAll();
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Collection<Creative> addCreative(@RequestBody User user){
        User u=us.getUser(user.getLogin());
        Creative crea=new Creative();
        crea.setAuthor(u);
        LinkedList<Chapter> list=new LinkedList<>();
        Chapter chapter=new Chapter();
        chapter.setNumber(1);
        list.add(chapter);
        crea.setChapters(list);
        cs.add(crea);
        return us.getUser(user.getLogin()).getCreatives();
    }
    @RequestMapping(method = RequestMethod.GET)
    public User getByLogin(@RequestParam("login") String s){
        return us.getUser(s);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<User> getAll(){
        return us.getAll();
    }
}
