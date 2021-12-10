package edu.upc.dsa.DAO;

import edu.upc.dsa.utils.ObjectHelper;
import edu.upc.dsa.utils.QueryHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


public class SessionImpl implements Session {
    private final Connection conn;

    final Logger logger = Logger.getLogger(SessionImpl.class.getName());

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);
        logger.info(insertQuery);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            int i = 1;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }
            logger.info(pstm.toString());
            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Object get(Object object) {

        Class theClass = object.getClass();
        String selectQuery = QueryHelper.createQuerySELECT(object);
        logger.info(selectQuery);
        String id = ObjectHelper.getId(object);

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            object = theClass.getDeclaredConstructor().newInstance();

            pstm = conn.prepareStatement(selectQuery);

            pstm.setObject(1, id);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            if (rs.next()) {

                rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, rs.getObject(i));
                }
                return object;

            } else {
                return null;
            }

        } catch (SQLException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getByParameter(Class theClass, String byParameter, Object byParameterValue) {

        String selectQuery = QueryHelper.createQuerySELECTbyParameter(theClass, byParameter);
        logger.info(selectQuery);

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            Object object = theClass.getDeclaredConstructor().newInstance();

            pstm = conn.prepareStatement(selectQuery);

            pstm.setObject(1, byParameterValue);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            if (rs.next()) {

                rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, rs.getObject(i));
                }
                return object;

            } else {
                return null;
            }

        } catch (SQLException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getParameterByParameter(Class theClass, String parameter, String byParameter, Object byParameterValue) {

        String selectQuery = QueryHelper.createQuerySELECTParameterByParameter(theClass, parameter, byParameter);
        logger.info(selectQuery);

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conn.prepareStatement(selectQuery);

            pstm.setObject(1, byParameterValue);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            if (rs.next()) {
                return rs.getObject(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Object object) {

        String updateQuery = QueryHelper.createQueryUPDATE(object);
        logger.info(updateQuery);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;

            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i++, ObjectHelper.getter(object, ObjectHelper.getFields(object)[0]));
            pstm.executeQuery();
            logger.info(pstm.toString());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateByParameter(Object object, String byParameter, Object byParameterValue) {

        String updateQuery = QueryHelper.createQueryUPDATEbyParameter(object.getClass(), byParameter);
        logger.info(updateQuery);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;

            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i++, ObjectHelper.getter(object, byParameterValue.toString()));
            pstm.executeQuery();
            logger.info(pstm.toString());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateParameterByParameter(Class theClass, String parameter, Object parameterValue, String byParameter, Object byParameterValue) {

        String updateQuery = QueryHelper.createQueryUPDATEParameterByParameter(theClass, parameter, byParameter);
        logger.info(updateQuery);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);

            pstm.setObject(1, parameterValue);
            pstm.setObject(2, byParameterValue);
            logger.info(pstm.toString());
            pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object object) {

        String deleteQuery = QueryHelper.createQueryDELETE(object);
        logger.info(deleteQuery);
        String id = ObjectHelper.getId(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(deleteQuery);

            pstm.setString(1, id);
            logger.info(pstm.toString());
            pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByParameter(Class theClass, String byParameter, Object byParameterValue) {

        String deleteQuery = QueryHelper.createQueryDELETEbyParameter(theClass, byParameter);
        logger.info(deleteQuery);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(deleteQuery);

            pstm.setObject(1, byParameterValue);
            logger.info(pstm.toString());
            pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Object> queryObjects(String query, Class theClass, List params) {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<Object> objectResult = new LinkedList<>();
        Object object = null;

        try{
            object = theClass.newInstance();

            pstm = conn.prepareStatement(query);

            int i = 1;
            for(Object o: params) {
                pstm.setObject(i, params.get(i-1));
                i++;
            }
            rs = pstm.executeQuery();

            while(rs.next()) {
                rsmd = rs.getMetaData();

                for(int j=1; j<=rsmd.getColumnCount(); j++) {
                    String name = rsmd.getColumnName(j);
                    ObjectHelper.setter(object,name, rs.getObject(j));
                }
                logger.info("Object added " +object);
                objectResult.add(object);
                object = theClass.newInstance();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return objectResult;
    }

    public HashMap<Integer, Object> FindAll(Class theClass) {

        String selectQuery = QueryHelper.createQuerySELECTAll(theClass);
        logger.info(selectQuery);

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        HashMap<Integer, Object> result = new HashMap<>();
        Object object = null;

        try {
            object = theClass.getDeclaredConstructor().newInstance();

            pstm = conn.prepareStatement(selectQuery);
            rs = pstm.executeQuery();

            while (rs.next()) {
                rsmd = rs.getMetaData();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, rs.getObject(i));
                }
                result.put((int) rs.getObject(1), object);
                object = theClass.getDeclaredConstructor().newInstance();
            }
            return result;

        } catch (SQLException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<Integer, Object> FindAllByParameter(Class theClass, String byParameter, Object byParameterValue) {

        String selectQuery = QueryHelper.createQuerySELECTbyParameter(theClass, byParameter);
        logger.info(selectQuery);

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        HashMap<Integer, Object> result = new HashMap<>();
        Object object = null;

        try {
            object = theClass.getDeclaredConstructor().newInstance();

            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, byParameterValue);
            rs = pstm.executeQuery();

            while (rs.next()) {
                rsmd = rs.getMetaData();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, rs.getObject(i));
                }
                if (rs.getObject(1) instanceof Integer) {
                    result.put((int) rs.getObject(1), object);
                } else {
                    System.out.println("Error, " + rs.getObject(1) + " is not an Integer");
                }
                object = theClass.getDeclaredConstructor().newInstance();
            }
            return result;

        } catch (SQLException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}