package example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saul on 11/20/16.
 */
@RestController
@RequestMapping("req")
public class RequestsController {
    @RequestMapping(value = "/req_token")
    public String getDomain() {
        try {
            int millis = (int) System.currentTimeMillis() * -1;
            int time = (int) millis / 1000;
            URL url = new URL("http://api.twitter.com/oauth/request_token?" +
                    "oauth_callback=https://10.42.0.169"+
            "&oauth_consumer_key=DeDfExndrtgc6cDV3mF3gvHGc"+
            "&oauth_nonce="+String.valueOf(millis)+
            "&oauth_signature="+
            "&oauth_signature_method=HMAC-SHA1"+
            "&oauth_timestamp="+String.valueOf(time)+
            "oauth_version=1.0");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();
            return conn.getResponseMessage();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/authenticate")
    public String authenticate(){
        return "";
    }
}
