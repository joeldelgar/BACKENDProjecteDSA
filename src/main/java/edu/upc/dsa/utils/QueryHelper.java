package edu.upc.dsa.utils;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" (");

        for (String field: ObjectHelper.getFields(entity)) {
            sb.append(field + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");

        for (String field: ObjectHelper.getFields(entity)) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQuerySELECTbyParameter(Class theClass, String byParameter) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + byParameter + " = ?");

        return sb.toString();
    }

    public static String createQuerySELECTbyTwoParameters(Class theClass, String byFirstParameter, String bySecondParameter) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + byFirstParameter + " = ?");
        sb.append(" AND " + bySecondParameter + " = ?");

        return sb.toString();
    }

    public static String createQuerySELECTParameterByParameter(Class theClass, String parameter, String byParameter) {

        StringBuffer sb = new StringBuffer("SELECT " + parameter + " FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + byParameter + " = ?");

        return sb.toString();
    }

    public static String createQuerySELECTAll(Class theClass) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());

        return sb.toString();
    }

    public static String createQueryORDERbyParameter(Class theClass, String byParameter) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" ORDER BY " + byParameter + " DESC");

        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" SET");

        for (String field: ObjectHelper.getFields(entity)) {
            sb.append(" " + field).append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEbyParameter(Object entity, String parameter) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" SET");

        for (String field: ObjectHelper.getFields(entity)) {
            sb.append(" " + field).append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE " + parameter + " = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEbyTwoParameters(Object entity, String byFirstParameter, String bySecondParameter) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" SET");

        for (String field: ObjectHelper.getFields(entity)) {
            sb.append(" " + field).append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE " + byFirstParameter + " = ?");
        sb.append(" AND " + bySecondParameter + " = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEParameterByParameter(Class theClass, String parameter, String byParameter) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(theClass.getSimpleName() + " SET " + parameter + " = ?");
        sb.append(" WHERE " + byParameter + " = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEParameterByTwoParameters(Class theClass, String parameter, String byFirstParameter, String bySecondParameter) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(theClass.getSimpleName() + " SET " + parameter + " = ?");
        sb.append(" WHERE " + byFirstParameter + " = ?");
        sb.append(" AND " + bySecondParameter + " = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity){

        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(entity.getClass().getSimpleName()).append(" WHERE ID = ?");

        return  sb.toString();
    }

    public static String createQueryDELETEbyParameter(Class theClass, String parameter){

        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + parameter + " = ?");

        return sb.toString();
    }

    public static String createQueryDELETEbyTwoParameters(Class theClass, String byFirstParameter, String bySecondParameter){

        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + byFirstParameter + " = ?");
        sb.append(" AND " + bySecondParameter + " = ?");

        return sb.toString();
    }
}
