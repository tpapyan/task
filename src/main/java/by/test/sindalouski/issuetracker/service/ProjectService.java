package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.ProjectDto;
import by.test.sindalouski.issuetracker.entity.Project;
import java.io.IOException;
import java.util.List;

public interface ProjectService {

	List<Project> listProjects();

    void addProject(ProjectDto projectDto) throws IOException;

    void remove(Integer id);

    void updateProject(ProjectDto projectDto);

}
