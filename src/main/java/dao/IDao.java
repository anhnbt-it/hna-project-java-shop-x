package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao<T, ID> {

    boolean save(T entity) throws SQLException;

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    long count();

    boolean deleteById(ID id);

    void delete(T entity);
}
