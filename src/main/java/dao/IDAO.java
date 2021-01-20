package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDAO<T, ID> {

    int save(T entity) throws SQLException;

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    long count();

    void deleteById(ID id);

    void delete(T entity);
}
