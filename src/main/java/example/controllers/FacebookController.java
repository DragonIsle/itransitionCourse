package example.controllers;

import example.enums.AuthType;
import example.enums.Role;
import example.models.User;
import example.service.UserService;
import example.session.MySessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by saul on 11/24/16.
 */
@RestController
@RequestMapping("facebook")
public class FacebookController {
    @Autowired
    private FacebookConnectionFactory factory;

    @Autowired
    private UserService us;

    @Resource(name = "session")
    private MySessionClass session;

    @RequestMapping(method = RequestMethod.POST)
    public String loggedIn(){
        OAuth2Operations operations=factory.getOAuthOperations();
        OAuth2Parameters params=new OAuth2Parameters();
        params.setRedirectUri("http://localhost/facebook");
        return operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void facebookAuth(@RequestParam("code") String code, @Autowired HttpServletResponse resp){
        AccessGrant grant=factory.getOAuthOperations().exchangeForAccess(code, "http://localhost/facebook", null);
        Connection<Facebook> connection=factory.createConnection(grant);
        FacebookProfile profile=connection.getApi().userOperations().getUserProfile();
        User u=us.getUser(String.valueOf(profile.getId()));
        if(u==null) {
            u=new User();
            u.setLogin(String.valueOf(profile.getId()));
            u.setName(profile.getName());
            u.setRole(String.valueOf(Role.USER));
            u.setType(String.valueOf(AuthType.FACEBOOK_AUTH));
            u.setPassword("");
            us.add(u);
        }
        session.setUser(u);
        try {
            resp.sendRedirect("index.html");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
