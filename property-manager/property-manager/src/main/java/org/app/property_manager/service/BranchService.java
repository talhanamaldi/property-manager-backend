package org.app.property_manager.service;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.Branch;
import org.app.property_manager.service.model.ResponseBase;

import java.util.List;

public interface BranchService {
    Branch findOne(long id) throws GeneralException, GeneralWarning;

    List<Branch> findAll() throws GeneralException, GeneralWarning;

    ResponseBase save(Branch branch) throws GeneralException, GeneralWarning;

    ResponseBase update(Branch branch) throws GeneralException, GeneralWarning;

    ResponseBase delete(long id) throws GeneralException, GeneralWarning;
}
