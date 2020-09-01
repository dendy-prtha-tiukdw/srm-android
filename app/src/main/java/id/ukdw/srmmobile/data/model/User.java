package id.ukdw.srmmobile.data.model;

import lombok.Data;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.model
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:02
 * <p>
 * Description : User model
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
