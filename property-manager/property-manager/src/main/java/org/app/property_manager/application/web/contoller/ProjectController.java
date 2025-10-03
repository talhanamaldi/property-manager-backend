package org.app.property_manager.application.web.contoller;

import jakarta.validation.Valid;
import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.Project;
import org.app.property_manager.service.ProjectService;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public Project findOne(@PathVariable long id) throws GeneralException, GeneralWarning {
        return projectService.findOne(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Project> findAll() throws GeneralException, GeneralWarning {
        return projectService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseBase save(@RequestBody @Valid Project project) throws GeneralException, GeneralWarning {
        return projectService.save(project);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseBase update(@RequestBody @Valid Project project) throws GeneralException, GeneralWarning {
        return projectService.update(project);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseBase delete(@RequestBody long id) throws GeneralException, GeneralWarning {
        return projectService.delete(id);
    }
}
