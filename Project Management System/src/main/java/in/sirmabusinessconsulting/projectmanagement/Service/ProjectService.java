package in.sirmabusinessconsulting.projectmanagement.Service;

import in.sirmabusinessconsulting.projectmanagement.Model.ProjectEntity;
import in.sirmabusinessconsulting.projectmanagement.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    @Autowired
    private ProjectRepository repository;



    // Create operation
    public ProjectEntity createProject(ProjectEntity project) {
        return repository.save(project);
    }

    // Read operations
    public List<ProjectEntity> getAllProjects() {
        return repository.findAll();
    }

    public Optional<ProjectEntity> getProjectById(Long id) {
        return repository.findById(id);
    }

    // Update operation
    public ProjectEntity updateProject(Long id, ProjectEntity updatedProject) {
        return repository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    project.setDescription(updatedProject.getDescription());
                    project.setStartDate(updatedProject.getStartDate());
                    project.setEndDate(updatedProject.getEndDate());
                    return repository.save(project);
                })
                .orElse(null);
    }

    // Delete operation
    public void deleteProject(Long id) {
        repository.deleteById(id);
    }








}
