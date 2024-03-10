package in.sirmabusinessconsulting.projectmanagement.Repository;

import in.sirmabusinessconsulting.projectmanagement.Model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
}
