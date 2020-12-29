package app.dao;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractHibernateDao<T> implements DAO<T>{

    private Class<T> clazz;

    protected void setClazz(Class< T > clazzToSet) {
        clazz = clazzToSet;
    }

    private final org.hibernate.Session getCurrentSession(){
        return Hibernate.getSession();
    }

    @Override
    public Optional<T> get(Serializable key) {
        if (exist(key)) {
            Session session = getCurrentSession();
               return session.byId(clazz).loadOptional(key);
        }
        return Optional.empty();
    }

    @Override
    public List<T> getAll() {
        List<T> resultList= getCurrentSession().createQuery("from " + clazz.getName()).list();
        return resultList;
    }



    @Override
    public boolean exist(Serializable id) {
        try(Session s = getCurrentSession())
        {
            return s.get(clazz, id)!=null;
        }
        catch(Exception e)
        {
            System.out.println("Can't check element existence in DB");
            return false;
        }
    }

    @Override
    public void save(T entity) {

       Session session = getCurrentSession();
       session.beginTransaction();
       session.save(entity);

        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
        }
    }

    public void update(T t) {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.merge(t);

        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T t) {

        Session session = getCurrentSession();
        session.beginTransaction();
        session.delete(t);
        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
        }
    }
}