package org.app.property_manager.service.implementation;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.common.retrieving.IClassRetrieving;
import org.app.property_manager.model.entity.app.Branch;
import org.app.property_manager.model.entity.app.Property;
import org.app.property_manager.repository.BranchRepository;
import org.app.property_manager.repository.PropertyRepository;
import org.app.property_manager.service.PropertyService;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final BranchRepository branchRepository;
    private final IClassRetrieving classRetrieving;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository,
                               BranchRepository branchRepository,
                               IClassRetrieving classRetrieving) {
        this.propertyRepository = propertyRepository;
        this.branchRepository = branchRepository;
        this.classRetrieving = classRetrieving;

    }

    @Override
    public Property findOne(long id) throws GeneralException, GeneralWarning {
        return propertyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Property> findAll() throws GeneralException, GeneralWarning {
        return propertyRepository.findAll();
    }

    @Override
    public ResponseBase save(Property property) throws GeneralException, GeneralWarning {
        if (property == null) throw new GeneralException("Property is null");
        if (property.getBranch() == null) throw new GeneralException("Branch is null");

        Branch branch = branchRepository.findById(property.getBranch().getId())
                .orElseThrow(() -> new GeneralException("Branch not found"));

        property.setBranch(branch);
        propertyRepository.save(property);
        return new ResponseBase("Saved successfully");
    }

    @Override
    public ResponseBase update(Property property) throws GeneralException, GeneralWarning {
        Property dataUpd = propertyRepository.findById(property.getId()).orElse(null);
        if (dataUpd == null) throw new GeneralException("Property not found");

        Map<String, Object> projectRetrieving = classRetrieving.getValue(property);

        for (String key : projectRetrieving.keySet()) {
            Object value = projectRetrieving.get(key);
            if (value != null) {
                classRetrieving.setValue(dataUpd, key, value);
            }
        }

        propertyRepository.save(dataUpd);
        return new ResponseBase("Updated successfully");
    }

    @Override
    public ResponseBase delete(long id) throws GeneralException, GeneralWarning {
        propertyRepository.deleteById(id);
        return new ResponseBase("Deleted successfully");
    }
}
