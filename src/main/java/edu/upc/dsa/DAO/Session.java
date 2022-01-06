package edu.upc.dsa.DAO;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {

    boolean create(Object object);
    void save(Object entity);
    void close();

    Object get(Object object); //in develop, not used, not tested
    Object getByParameter(Class theClass, String byParameter, Object byParameterValue);
    Object getByTwoParameters(Class theClass, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue);
    Object getParameterByParameter(Class theClass, String parameter, String byParameter, Object byParameterValue);

    boolean update(Object object);
    boolean updateByParameter(Object object, String byParameter, Object byParameterValue);
    boolean updateByTwoParameters(Object object, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue);
    boolean updateParameterByParameter(Class theClass, String parameter, Object parameterValue, String byParameter, Object byParameterValue);
    boolean updateParameterByTwoParameters(Class theClass, String parameter, Object parameterValue, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue);

    boolean delete(Object object);
    boolean deleteByParameter(Class theClass, String byParameter, Object byParameterValue);
    boolean deleteByTwoParameters(Class theClass, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue);

    List<Object> queryObjects(String query, Class theClass, List params);

    HashMap<Integer, Object> FindAll(Class theClass);
    HashMap<Integer, Object> FindAllByParameter(Class theClass, String byParameter, Object byParameterValue);
}
