package example.controllers;

import example.models.Creative;
import example.models.User;
import example.service.UserService;
import example.session.MySessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by saul on 11/24/16.
 */
@RestController
@RequestMapping("session")
public class SessionController {
    @Resource(name="session")
    private MySessionClass session;

    @Autowired
    private UserService us;

    @RequestMapping(method = RequestMethod.POST)
    public User getCurrentUser(){
        return session.getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public void logout(){
        session.setUser(null);
    }
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public Boolean check(@RequestParam Integer creativeId){
        User u=session.getUser();
        for(Creative creative:u.getCreatives()){
            if(creative.getId()==creativeId) return true;
        }
        return false;
    }

    @RequestMapping(value = "/style", method = RequestMethod.GET)
    public String getStyle(){
        return session.getUser().getTheme();
    }

    @RequestMapping(value = "/change/style", method = RequestMethod.GET)
    public String changeStyle(@RequestParam("style") String style){
        User u=us.getUser(session.getUser().getLogin());
        u.setTheme("css/"+style+".css");
        us.update(u);
        session.setUser(us.getUser(u.getLogin()));
        return u.getTheme();
    }
}
