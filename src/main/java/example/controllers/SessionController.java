package example.controllers;

import example.models.Creative;
import example.models.User;
import example.session.MySessionClass;
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
}
