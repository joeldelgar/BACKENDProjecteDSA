package edu.upc.dsa.DAO.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) {
            sFields[i++] = f.getName();
        }

        return sFields;
    }

    private static String setMethodName(String property){
        return "set"+property.substring(0,1).toUpperCase() + property.substring(1);
    }

    public static void setter(Object object, String property, Object value) throws NoSuchMethodException {
        Class theClass = object.getClass();
        String getMethod = setMethodName(property);

        Method setter = null;
        if (value.getClass() == String.class) {
            setter = theClass.getMethod(getMethod, String.class);
        } else if (value.getClass() == Double.class) {
            setter = theClass.getMethod(getMethod, double.class);
        }

        try {
            setter.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMethodName(String property){
        return "get"+property.substring(0,1).toUpperCase() + property.substring(1);
    }

    public static Object getter(Object object, String property) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object ret = null;
        Class theClass = object.getClass();
        String setMethod = getMethodName(property);
        Method getter = theClass.getMethod(setMethod,null);

        try{
            ret = getter.invoke(object,null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}