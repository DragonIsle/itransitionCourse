package example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by saul on 11/24/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public void redirect(@Autowired HttpServletResponse response){
        try {
            response.sendRedirect("index.html");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
