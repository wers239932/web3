package beans;

import database.Database;
import database.User;
import encoding.Hasher;
import jakarta.ejb.Stateful;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateful
public class UserDBBean {

    public User createUser(String login, String password) { //Ñîçäàíèå ïîëüçîâàòåëÿ
        try {
            User user = new User();
            user.setUsername(login);
            user.setPasswd_hash(Hasher.SHA(password));
            user.setLogged_in(false);
            Session session = Database.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();

            return user;

        } catch (PersistenceException e) {
            return null;
        }
    }


    public void updateUserLoggedIn(Integer id, Boolean loggedIn) {
        try {
            Session session = Database.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.createQuery("update User set logged_in = :logged_in where id = :id")
                    .setParameter("logged_in", loggedIn)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
            session.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Boolean isUserLoggedIn(Integer id) {
        try {
            Session session = Database.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Boolean loggedIn =(Boolean) session.createQuery("select logged_in from User where id = :id").setParameter("id", id).getSingleResult();
            tx.commit();
            session.close();
            return loggedIn;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkPassword(String login, String password) {
        User user = findUserByName(login);
        return user.getPasswd_hash().equals(Hasher.SHA(password));
    }

    public Boolean ifExist(String username) {
        try {
            User user = (User) Database.getSessionFactory().openSession().createQuery("from User where username=:username").setParameter("username", username).uniqueResult();

            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public User findUserById(int id) {
        User user = (User) Database.getSessionFactory().openSession().createQuery("from User where id=:id").setParameter("id", id).uniqueResult();
        return user;
    }

    public User findUserByName(String username) {
        User user = (User) Database.getSessionFactory().openSession().createQuery("from User where username=:username").setParameter("username", username).uniqueResult();
        return user;
    }
}
