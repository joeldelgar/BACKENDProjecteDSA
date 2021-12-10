package edu.upc.dsa.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;

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

    public static String getId(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String id = new String();

        try {
            id = String.valueOf(fields[0].getInt(entity));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return id;
    }

    private static String getSetter(String property){
        return "set"+property.substring(0,1).toUpperCase() + property.substring(1);
    }

    public static void setter(Object object, String property, Object value) {

        Class theClass = object.getClass();
        String getSetterString = getSetter(property);

        try {
            Method setter = null;
            if (value.getClass() == Integer.class) {
                setter = theClass.getMethod(getSetterString, int.class);
            } else {
                setter = theClass.getMethod(getSetterString, value.getClass());
            }
            setter.invoke(object, value);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static String getGetter(String property){
        return "get"+property.substring(0,1).toUpperCase() + property.substring(1);
    }

    public static Object getter(Object object, String property) {

        Object ret = null;
        Class theClass = object.getClass();
        String getGetterString = getGetter(property);

        try{
            Method getter = theClass.getMethod(getGetterString,null);
            ret = getter.invoke(object);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }
}