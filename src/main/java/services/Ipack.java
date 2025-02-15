package services;

import java.sql.SQLException;
import java.util.List;

public interface Ipack <T> {
    void create(T t) throws SQLException;
    void update(T t)throws SQLException;
    void delete(int id)throws SQLException;
    List<T> getAll() throws SQLException;
}
