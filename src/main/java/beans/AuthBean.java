package beans;

import database.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.validation.constraints.Null;

@Stateless
public class AuthBean {

    @EJB
    private UserDBBean userDB;

    public String register(String login, String password) {
        Boolean userExists = userDB.ifExist(login);
        if (!userExists) {
            User user = userDB.createUser(login, password);
            if (user == null) {
                return null;
            } else return String.valueOf(user.getId());
        }
        return null;

    }

    public Boolean logout(Integer id) {
        Boolean userExists = userDB.findUserById(id) != null;
        if (!userExists) {
            return false;
        } else {
            userDB.findUserById(id).setLogged_in(false);
            userDB.updateUserLoggedIn(id, false);
            return true;
        }
    }

    public String login(String login, String password) {
        Boolean userExists = userDB.ifExist(login);

        if (!userExists) {
            return null;
        } else if (userDB.checkPassword(login, password)) {
            userDB.findUserByName(login).setLogged_in(true);
            userDB.updateUserLoggedIn(userDB.findUserByName(login).getId(), true);
            //userDB.updateUser(userDB.findUserByName(login));

            return String.valueOf(userDB.findUserByName(login).getId());
        }
        return null;
    }

    public User getUserById(int userId) {
        return userDB.findUserById(userId);
    }
    public Boolean isLoggedIn(Integer id) {
        return this.userDB.isUserLoggedIn(id);
    }
}
