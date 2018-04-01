package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.ProjectDto;
import by.test.sindalouski.issuetracker.entity.Project;
import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.ProjectRepository;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Project> listProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void addProject(ProjectDto projectDto) throws IOException {

        Project project = new Project();
        project.setDescription(projectDto.getDescription());
        project.setProjectName(projectDto.getProjectName());
        User user = userRepository.findOne(projectDto.getUserId());
        project.setUser(user);
        projectRepository.save(project);
    }

    @Override
    public void remove(Integer id) {

        projectRepository.delete(id);
    }

    @Override
    public void updateProject(ProjectDto projectDto) {

        Project project = projectRepository.findOne(projectDto.getId());
        project.setDescription(projectDto.getDescription());
        project.setProjectName(projectDto.getProjectName());
        User user = userRepository.findOne(projectDto.getUserId());
        project.setUser(user);
        projectRepository.save(project);
    }
}


