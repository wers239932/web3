package jwt;

import java.security.Principal;

public class UserJWT implements Principal {
    private String id;

    public UserJWT(String id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return id;
    }
}
