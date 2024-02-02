package am.picsartacademy.hrmanagement.service;

import am.picsartacademy.hrmanagement.dao.ProjectDao;
import am.picsartacademy.hrmanagement.entity.Project;

import java.util.List;

public class ProjectService {

    private final ProjectDao projectDao;

    public ProjectService() {
        this.projectDao = new ProjectDao();
    }

    public void saveProject(Project project) {
        if (validateProject(project)) {
            projectDao.saveProject(project);
            System.out.println("Project saved successfully.");
        } else {
            System.out.println("Project validation failed.");
        }
    }

    public void updateProject(Project project) {
        if (validateProject(project)) {
            projectDao.updateProject(project);
            System.out.println("Project updated successfully.");
        } else {
            System.out.println("Project validation failed.");
        }
    }

    public Project findProjectById(Long id) {
        return projectDao.findProjectById(id);
    }

    public void deleteProject(Long id) {
        Project project = projectDao.findProjectById(id);
        if (project != null) {
            projectDao.deleteProject(project);
            System.out.println("Project deleted successfully.");
        } else {
            System.out.println("Project not found.");
        }
    }

    public List<Project> findAllProjects() {
        return projectDao.findAllProjects();
    }

    // Validation logic for Project
    private boolean validateProject(Project project) {

        if (project.getProjectName() == null || project.getProjectName().trim().isEmpty()) {
            System.out.println("Project name is required.");
            return false;
        }

        if (project.getStartDate() == null || project.getEndDate() == null) {
            System.out.println("Both start and end dates are required.");
            return false;
        }
        if (project.getEndDate().before(project.getStartDate())) {
            System.out.println("End date cannot be before start date.");
            return false;
        }
        return true;
    }
}
