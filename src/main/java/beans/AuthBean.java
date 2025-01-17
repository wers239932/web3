package beans;

import database.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthBean {

    @EJB
    private UserDBBean userDB;

    public Integer register(String login, String password) {
        Boolean userExists = userDB.ifExist(login);
        if (!userExists) {
            User user = userDB.createUser(login, password);
            if (user == null) {
                return -1;
            } else return user.getId();
        }
        return -1;

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

    public Integer login(String login, String password) {
        Boolean userExists = userDB.ifExist(login);

        if (!userExists) {
            return -1;
        } else if (userDB.checkPassword(login, password)) {
            userDB.findUserByName(login).setLogged_in(true);
            userDB.updateUserLoggedIn(userDB.findUserByName(login).getId(), true);
            //userDB.updateUser(userDB.findUserByName(login));

            return userDB.findUserByName(login).getId();
        }
        return -1;
    }

    public User getUserById(int userId) {
        return userDB.findUserById(userId);
    }
    public Boolean isLoggedIn(Integer id) {
        return this.userDB.isUserLoggedIn(id);
    }
}
