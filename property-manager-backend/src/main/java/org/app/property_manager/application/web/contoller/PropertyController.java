package org.app.property_manager.application.web.contoller;

import jakarta.validation.Valid;
import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.Property;
import org.app.property_manager.service.PropertyService;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public Property findOne(@PathVariable long id) throws GeneralException, GeneralWarning {
        return propertyService.findOne(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Property> findAll() throws GeneralException, GeneralWarning {
        return propertyService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseBase save(@RequestBody @Valid Property property) throws GeneralException, GeneralWarning {
        return propertyService.save(property);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBase update(@RequestBody @Valid Property property) throws GeneralException, GeneralWarning {
        return propertyService.update(property);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseBase delete(@RequestBody long id) throws GeneralException, GeneralWarning {
        return propertyService.delete(id);
    }

}
