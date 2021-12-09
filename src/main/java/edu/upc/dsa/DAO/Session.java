package edu.upc.dsa.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException;
    void close();
    Object get(Object object, int ID) throws SQLException, NoSuchMethodException;
    void update(Object object, String ID) throws SQLException;
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
