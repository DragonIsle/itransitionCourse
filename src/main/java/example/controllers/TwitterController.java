package example.controllers;

import example.enums.AuthType;
import example.enums.Role;
import example.models.User;
import example.service.UserService;
import example.session.MySessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by saul on 11/15/16.
 */
@RestController
@RequestMapping("twitter")
public class TwitterController {

    @Autowired
    private TwitterConnectionFactory factory;

    @Autowired
    private UserService userService;

    @Resource(name = "session")
    private MySessionClass session;

    @RequestMapping(method = RequestMethod.POST)
    public String loggedIn(){
        OAuth1Operations operations=factory.getOAuthOperations();
        OAuthToken token=operations.fetchRequestToken("http://localhost/twitter", null);
        return operations.buildAuthorizeUrl(token.getValue(), OAuth1Parameters.NONE);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void twitterAuth(@RequestParam("oauth_token") String oauthToken, @RequestParam("oauth_verifier") String verifier, @Autowired HttpServletResponse resp){
        AuthorizedRequestToken token1=new AuthorizedRequestToken(new OAuthToken(oauthToken, "Wa1Bvi57F9HZE1sjs9JSYko5JJzOHk460ODcXOOzlsD0aOw2yP"), verifier);
        OAuthToken token2=factory.getOAuthOperations().exchangeForAccessToken(token1, null);
        Connection<Twitter> connection=factory.createConnection(token2);
        TwitterProfile profile=connection.getApi().userOperations().getUserProfile();
        User u=userService.getUser(String.valueOf(profile.getId()));
        if(u==null) {
            u=new User();
            u.setAvatarId(profile.getProfileImageUrl());
            u.setLogin(String.valueOf(profile.getId()));
            u.setName(profile.getName());
            u.setRole(String.valueOf(Role.USER));
            u.setType(String.valueOf(AuthType.TWITTER_AUTH));
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
