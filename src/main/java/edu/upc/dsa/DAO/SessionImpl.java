package edu.upc.dsa.DAO;

import edu.upc.dsa.DAO.utils.ObjectHelper;
import edu.upc.dsa.DAO.utils.QueryHelper;
import edu.upc.dsa.UserManagerImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


public class SessionImpl implements Session {
    private final Connection conn;

    final Logger log = Logger.getLogger(UserManagerImpl.class);
    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        String insertQuery = QueryHelper.createQueryINSERT(entity);
        log.info(insertQuery);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }
            log.info(pstm.toString());
            pstm.executeUpdate();
            //pstm.executeQuery();

        } catch (Exception e) {
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

    public Object get(Object object, int ID) throws SQLException, NoSuchMethodException {
        Class theClass = object.getClass();
        String selectQuery = QueryHelper.createQuerySELECT(object);
        log.info(selectQuery);

        Object entity = null;

        PreparedStatement pstm = null;
        ResultSet result = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, ID);
            result = pstm.executeQuery();
            log.info("Query OK");

            while (result.next()){
                Field[] fields = theClass.getDeclaredFields();
                result.getString(1);
                for(int i = 0; i < fields.length; i ++){
                    ResultSetMetaData rsmd = result.getMetaData();
                    String name = rsmd.getColumnName(i+2);
                    log.info("Column name: "+ name);
                    ObjectHelper.setter(object, name, result.getObject(i+2));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    public void update(Object object, int ID) throws SQLException {
        String updateQuery = QueryHelper.createQueryUPDATE(object);
        log.info(updateQuery);

        PreparedStatement pstm = null;
        ResultSet result = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;

            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i,ObjectHelper.getter(object,ID));
            result = pstm.executeQuery();
            log.info("Query OK");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Object object) {

    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}