package app.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

        Optional<T> get(Serializable key);

        List<T> getAll();

        boolean exist(Serializable key);

         void save(T t);

        void update(T t);

        void delete(T t);


}
