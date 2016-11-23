package example.controllers;

import example.enums.AuthType;
import example.enums.Role;
import example.models.User;
import example.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by saul on 11/12/16.
 */
@RestController
@RequestMapping("reg")
public class RegController {
    @Autowired
    private UserService us;
    @RequestMapping(method = RequestMethod.POST)
    public Boolean registration(@RequestBody User user){
        try {
            user.setRole(String.valueOf(Role.USER));
            user.setType(String.valueOf(AuthType.DEFAULT_AUTH));
            user.setName(user.getLogin());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            us.add(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
