package app.dao;

public class UniqueHibernateDao<T> extends AbstractHibernateDao implements DAO{

    public UniqueHibernateDao(Class<T> clazz) {
       setClazz(clazz);
    }
}
