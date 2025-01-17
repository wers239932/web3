package beans;

import database.Database;
import database.Result;
import jakarta.ejb.Stateful;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import validation.Validator;

import java.time.LocalDateTime;
import java.util.List;

@Stateful
public class PointDBBean {
    public Result createPoint(Float x, Float y, Float r, Integer user, Long timestart) {

        try {
            final Result point = new Result();
            point.setOwnerId(user);
            point.setR(r);
            point.setX(x);
            point.setY(y);
            if (Validator.validate(point)) {
                point.setCreationTime(LocalDateTime.now());
                point.setTimeWork(LocalDateTime.now().getNano() - timestart);
                point.setResult(Validator.check(point));

                Session session = Database.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.persist(point);
                tx.commit();
                session.close();

                return point;
            } else return null;
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List getByOwner(Integer owner) {
        return Database.getSessionFactory().openSession().createQuery("from Result where ownerId = :ownerId").setParameter("ownerId", owner).list();
    }
}
