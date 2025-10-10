package org.app.property_manager.service.implementation;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.common.retrieving.IClassRetrieving;
import org.app.property_manager.model.entity.app.Project;
import org.app.property_manager.repository.ProjectRepository;
import org.app.property_manager.service.ProjectService;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;

    IClassRetrieving classRetrieving;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              IClassRetrieving classRetrieving) {
        this.projectRepository = projectRepository;
        this.classRetrieving = classRetrieving;
    }

    @Override
    public Project findOne(long id) throws GeneralException, GeneralWarning {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> findAll() throws GeneralException, GeneralWarning {
        return projectRepository.findAll();
    }

    @Override
    public ResponseBase save(Project project) throws GeneralException, GeneralWarning {
        if (project == null) {
            throw new GeneralException("Project is null");
        }
        projectRepository.save(project);
        return new ResponseBase("Saved successfully");
    }

    @Override
    public ResponseBase update(Project project) throws GeneralException, GeneralWarning {

        Project dataUpd = projectRepository.findById(project.getId()).orElse(null);
        if (dataUpd == null) {
            throw new GeneralException("Project not found");
        }

        Map<String, Object> projectRetrieving=classRetrieving.getValue(project);

        for (String key :  projectRetrieving.keySet())
        {
            Object value = projectRetrieving.get(key);
            if (value != null) {
                classRetrieving.setValue(dataUpd, key, value);
            }
        }
        projectRepository.save(dataUpd);

        return new ResponseBase("Updated successfully");
    }

    @Override
    public ResponseBase delete(long id) throws GeneralException, GeneralWarning {
        projectRepository.deleteById(id);
        return new ResponseBase("Deleted successfully");
    }


}
