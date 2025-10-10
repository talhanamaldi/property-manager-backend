package org.app.property_manager.service;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.Property;
import org.app.property_manager.service.model.ResponseBase;

import java.util.List;

public interface PropertyService {

    Property findOne(long id) throws GeneralException, GeneralWarning;

    List<Property> findAll() throws GeneralException, GeneralWarning;

    ResponseBase save(Property property) throws GeneralException, GeneralWarning;

    ResponseBase update(Property property) throws GeneralException, GeneralWarning;

    ResponseBase delete(long id) throws GeneralException, GeneralWarning;
}
