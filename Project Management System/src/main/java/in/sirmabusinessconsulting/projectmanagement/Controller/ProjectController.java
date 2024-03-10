package in.sirmabusinessconsulting.projectmanagement.Controller;

import in.sirmabusinessconsulting.projectmanagement.Model.ProjectEntity;
import in.sirmabusinessconsulting.projectmanagement.Service.ProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Api(tags = "Project Management", description = "Endpoints for managing projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new project", notes = "Create a new project with the provided details")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Project created successfully"),
            @ApiResponse(code = 400, message = "Bad request, validation error")
    })
    public ResponseEntity<ProjectEntity> createProject(@Validated @RequestBody ProjectEntity project) {
        ProjectEntity createdProject = service.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping("/findall")
    @ApiOperation(value = "Get all projects", notes = "Get a list of all projects")
    @ApiResponse(code = 200, message = "List of projects retrieved successfully")
    public ResponseEntity<List<ProjectEntity>> getAllProjects() {
        List<ProjectEntity> projects = service.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get project by ID", notes = "Retrieve a project by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Project retrieved successfully"),
            @ApiResponse(code = 404, message = "Project not found")
    })
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable Long id) {
        Optional<ProjectEntity> project = service.getProjectById(id);
        return project.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a project", notes = "Update an existing project with the provided details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Project updated successfully"),
            @ApiResponse(code = 400, message = "Bad request, validation error"),
            @ApiResponse(code = 404, message = "Project not found")
    })
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable Long id, @Validated @RequestBody ProjectEntity updatedProject) {
        ProjectEntity updated = service.updateProject(id, updatedProject);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a project", notes = "Delete a project by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Project deleted successfully"),
            @ApiResponse(code = 404, message = "Project not found")
    })
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
