package org.app.property_manager.common.retrieving;

import lombok.extern.slf4j.Slf4j;
import org.app.property_manager.common.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class ClassRetrieving implements IClassRetrieving {

    @Override
    public boolean  setValue (Object object, String fieldName, Object fieldValue) throws GeneralException {

        if (fieldName.equals("id") || fieldName.equals("username") || fieldName.equals("isDeleted")) {
            return false;
        }

        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (Exception ex) {
                    field = object.getClass().getSuperclass().getDeclaredField(fieldName);
                }
                field.setAccessible(true);

                Type type = field.getGenericType();
                if (String.class.equals(type)) {
                    field.set(object, fieldValue.toString());
                } else if ((new Date()).getClass().equals(type)) {
                    field.set(object, fieldValue);
                } else if (Integer.class.equals(type)) {
                    field.set(object, fieldValue);
                } else if (Boolean.class.equals(type)) {
                    field.set(object, fieldValue);
                } else {
                    field.set(object, fieldValue);
                }

                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean setAllValue (Object object, String fieldName, Object fieldValue) {

        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (Exception ex) {
                    field = object.getClass().getSuperclass().getDeclaredField(fieldName);
                }
                field.setAccessible(true);

                Type type = field.getGenericType();
                if (String.class.equals(type)) {
                    field.set(object, fieldValue.toString());
                } else if ((new Date()).getClass().equals(type)) {
                    field.set(object, fieldValue);
                } else if (Integer.class.equals(type)) {
                    field.set(object, fieldValue);
                } else if (Boolean.class.equals(type)) {
                    field.set(object, fieldValue);
                } else {
                    field.set(object, fieldValue);
                }

                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getValue (Object object) {
        Map<String, Object> params = new HashMap<>();
        Class<?> clazz = object.getClass();
        if (clazz != null) {
            for (Field item : object.getClass().getDeclaredFields()) {
                String itemLabel = item.getName();
                if (itemLabel.equals("id") || itemLabel.equals("username") || itemLabel.equals("isDeleted")) {
                    continue;
                }
                if (java.lang.reflect.Modifier.isStatic(item.getModifiers())) {
                    continue;
                }
                Object itemValue;
                item.setAccessible(true);
                try {
                    itemValue=item.get(object);
                    if (itemValue != null) {
                        params.put(itemLabel, itemValue);
                    }
                } catch (Exception ex) {
                    log.error("Retrieving" + ex.getMessage());
                }
            }
            for (Field item : object.getClass().getSuperclass().getDeclaredFields()) {
                String itemLabel = item.getName();
                if (itemLabel.equals("id") || itemLabel.equals("username")) {
                    continue;
                }
                Object itemValue;
                item.setAccessible(true);
                try {
                    itemValue=item.get(object);
                    if (itemValue != null) {
                        params.put(itemLabel, itemValue);
                    }
                } catch (Exception ex) {
                    log.error("Retrieving Superclass" + ex.getMessage());
                }
            }
        }
        return params;
    }

    @Override
    public Long getValueId(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz != null) {
            for (Field item : object.getClass().getSuperclass().getDeclaredFields()) {
                String itemLabel = item.getName();
                if (itemLabel.equals("id")) {
                    Object itemValue;
                    item.setAccessible(true);
                    try {
                        itemValue=item.get(object);
                        if (itemValue != null) {
                            return (Long) itemValue;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        return null;
    }
}
