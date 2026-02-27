package lk.jiat.network.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Hibernate configuration failed" + e.getMessage());

        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
