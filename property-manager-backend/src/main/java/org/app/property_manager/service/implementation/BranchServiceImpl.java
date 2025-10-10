package org.app.property_manager.service.implementation;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.common.retrieving.IClassRetrieving;
import org.app.property_manager.model.entity.app.Branch;
import org.app.property_manager.model.entity.app.Project;
import org.app.property_manager.repository.BranchRepository;
import org.app.property_manager.repository.ProjectRepository;
import org.app.property_manager.service.BranchService;
import org.app.property_manager.service.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final IClassRetrieving classRetrieving;
    private final ProjectRepository projectRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository,
                             IClassRetrieving classRetrieving, ProjectRepository projectRepository) {
        this.branchRepository = branchRepository;
        this.classRetrieving = classRetrieving;
        this.projectRepository = projectRepository;
    }

    @Override
    public Branch findOne(long id) throws GeneralException, GeneralWarning {
        return branchRepository.findById(id).orElse(null) ;
    }

    @Override
    public List<Branch> findAll() throws GeneralException, GeneralWarning {
        return branchRepository.findAll();
    }

    @Override
    public ResponseBase save(Branch branch) throws GeneralException, GeneralWarning {

        if(branch == null) throw new GeneralException("Branch is null");
        if(branch.getProject() == null) throw new GeneralException("Project is null");

        Project project = projectRepository.findById(branch.getProject().getId())
                .orElseThrow(() -> new GeneralException("Project not found"));

        branch.setProject(project);
        branchRepository.save(branch);
        return new ResponseBase("Saved successfully");
    }

    @Override
    public ResponseBase update(Branch branch) throws GeneralException, GeneralWarning {
        Branch dataUpd = branchRepository.findById(branch.getId()).orElse(null);
        if(dataUpd == null) {
            throw new GeneralException("Branch not found");
        }

        Map<String, Object> projectRetrieving=classRetrieving.getValue(branch);

        for (String key :  projectRetrieving.keySet())
        {
            Object value = projectRetrieving.get(key);
            if (value != null) {
                classRetrieving.setValue(dataUpd, key, value);
            }
        }

        branchRepository.save(dataUpd);
        return new ResponseBase("Updated successfully");
    }

    @Override
    public ResponseBase delete(long id) throws GeneralException, GeneralWarning {
        branchRepository.deleteById(id);
        return new ResponseBase("Deleted successfully");
    }


}
