package id.ukdw.srmmobile.model;

import lombok.Data;

/**
 * Created by Dendy Prtha on 7/28/2020.
 * description : TODO
 */
@Data
public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
