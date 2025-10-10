package org.app.property_manager.common.retrieving;

import org.app.property_manager.common.exception.GeneralException;

import java.util.Map;

/**
 * @author murat
 */
public interface IClassRetrieving {
    boolean  setValue (Object object, String fieldName, Object fieldValue) throws GeneralException;
    boolean setAllValue (Object object, String fieldName, Object fieldValue);
    Map<String, Object> getValue (Object object);
    Long getValueId (Object object);
}
