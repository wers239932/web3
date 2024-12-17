package repository;

import beans.Result;
import jakarta.enterprise.context.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import validation.Validator;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class Repository implements Serializable {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        System.out.println("getSessionFactory");
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Result.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Some problems: " + e);
            }
        }
        return sessionFactory;
    }

    public Result getById(int id) {
        Session session = getSessionFactory().openSession();
        var res = session.get(Result.class, id);
        session.close();
        return res;
    }

    public List<Result> getAll() {
        Session session = getSessionFactory().openSession();
        var res = session.createQuery("from Result", Result.class).list();
        session.close();
        return res;
    }

    public void save(Result point) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(point);
        tx1.commit();
        session.close();
    }
    public void clear() {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from Result").executeUpdate();
        tx1.commit();
        session.close();
    }
    public void updateAll(float radius) {
        List<Result> points = getAll();
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        for (Result point : points) {
            point.setR(radius);
            point.setResult(Validator.check(point));
            session.saveOrUpdate(point);
        }
        tx1.commit();
        session.close();
    }
}
