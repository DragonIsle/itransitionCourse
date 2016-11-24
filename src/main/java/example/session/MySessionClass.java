package example.session;

import example.models.User;

/**
 * Created by saul on 11/24/16.
 */
public class MySessionClass {
   private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {

        return user;
    }
}
