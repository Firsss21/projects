package app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {

    private static SessionFactory sessionFactory;

    private Hibernate() {
        try
        {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        catch(Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        if (sessionFactory == null) { // потоки здесь ждут, пока освободится
            synchronized (Hibernate.class) {
                if (sessionFactory == null) {
                    new Hibernate();
                }
            }
        }
        return sessionFactory.openSession();
    }
}
