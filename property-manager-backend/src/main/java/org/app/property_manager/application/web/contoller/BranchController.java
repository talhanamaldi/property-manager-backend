package org.app.property_manager.application.web.contoller;

import jakarta.validation.Valid;
import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.Branch;
import org.app.property_manager.service.BranchService;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public Branch findOne(@PathVariable long id) throws GeneralException, GeneralWarning {
        return branchService.findOne(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Branch> findAll() throws GeneralException, GeneralWarning{
     return branchService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseBase save(@RequestBody @Valid Branch branch) throws GeneralException, GeneralWarning{
        return branchService.save(branch);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBase update(@RequestBody @Valid Branch branch) throws GeneralException, GeneralWarning{
        return branchService.update(branch);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseBase delete(@RequestBody long id) throws GeneralException, GeneralWarning{
        return branchService.delete(id);
    }
}
