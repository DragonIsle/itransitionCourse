package example.controllers;

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

    @RequestMapping(method = RequestMethod.POST)
    public String loggedIn(@Autowired HttpServletResponse response){
        OAuth1Operations operations=factory.getOAuthOperations();
        OAuthToken token=operations.fetchRequestToken("http://localhost:8080/twitter", null);
        return operations.buildAuthorizeUrl(token.getValue(), OAuth1Parameters.NONE);
        /*try {
            response.sendRedirect(authorizeUrl);
        }catch(Exception e){
            e.printStackTrace();
        }
        AuthorizedRequestToken token1=new AuthorizedRequestToken(token, "");
        OAuthToken token2=operations.exchangeForAccessToken(token1, null);
        Connection<Twitter> connection=factory.createConnection(token2);
        return connection.getApi().userOperations().getUserProfile().getName();
        */
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUserName(@RequestParam("oauth_token") String oauthToken, @RequestParam("oauth_verifier") String verifier){

        AuthorizedRequestToken token1=new AuthorizedRequestToken(new OAuthToken(oauthToken, "Wa1Bvi57F9HZE1sjs9JSYko5JJzOHk460ODcXOOzlsD0aOw2yP"), verifier);
        OAuthToken token2=factory.getOAuthOperations().exchangeForAccessToken(token1, null);
        Connection<Twitter> connection=factory.createConnection(token2);
        return connection.getApi().userOperations().getUserProfile().getName();
    }
}
