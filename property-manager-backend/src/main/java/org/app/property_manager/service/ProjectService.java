package org.app.property_manager.service;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.Project;
import org.app.property_manager.service.model.ResponseBase;

import java.util.List;

public interface ProjectService {
    Project findOne(long id) throws GeneralException, GeneralWarning;

    List<Project> findAll() throws GeneralException, GeneralWarning;

    ResponseBase save(Project project) throws GeneralException, GeneralWarning;

    ResponseBase update(Project project) throws GeneralException, GeneralWarning;

    ResponseBase delete(long id) throws GeneralException, GeneralWarning;
}
