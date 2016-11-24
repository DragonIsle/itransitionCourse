package example.controllers;

import example.enums.AuthType;
import example.enums.Role;
import example.models.User;
import example.service.UserService;
import example.session.MySessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by saul on 11/24/16.
 */
@RestController
@RequestMapping("vk")
public class VKController {
    @Autowired
    private VKontakteConnectionFactory factory;

    @Autowired
    private UserService userService;

    @Resource(name = "session")
    private MySessionClass session;

    @RequestMapping(method = RequestMethod.POST)
    public String loggedIn(){
        OAuth2Operations operations=factory.getOAuthOperations();
        OAuth2Parameters params=new OAuth2Parameters();
        params.setRedirectUri("http://localhost/vk");
        return operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void vkAuth(@RequestParam("code") String code, @Autowired HttpServletResponse resp){
        AccessGrant grant=factory.getOAuthOperations().exchangeForAccess(code, "http://localhost/vk", null);
        Connection<VKontakte> connection=factory.createConnection(grant);
        VKontakteProfile profile=connection.getApi().usersOperations().getProfile();
        User u=userService.getUser(String.valueOf(profile.getUid()));
        if(u==null) {
            u=new User();
            u.setLogin(String.valueOf(profile.getUid()));
            u.setName(profile.getFirstName()+" "+profile.getLastName());
            u.setRole(String.valueOf(Role.USER));
            u.setType(String.valueOf(AuthType.VK_AUTH));
            u.setPassword("");
            userService.add(u);
        }
        session.setUser(u);
        try {
            resp.sendRedirect("index.html");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
