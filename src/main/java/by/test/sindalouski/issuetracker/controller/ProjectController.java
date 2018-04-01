package by.test.sindalouski.issuetracker.controller;


import by.test.sindalouski.issuetracker.dto.ProjectDto;
import by.test.sindalouski.issuetracker.entity.Project;
import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.ProjectRepository;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProjectController {

    private ProjectService projectService;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService,
                             UserRepository userRepository,
                             ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String allProjects(Model model,
                        HttpSession session) {

    	List<Project> projects = projectService.listProjects();
        model.addAttribute("projects", projects);

        return "project/projects";
    }

    @GetMapping("/add")
    public String projectForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "project/addproject";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute("project") ProjectDto projectDto) throws IOException {
        projectService.addProject(projectDto);
        return "redirect:/project";
    }

    @GetMapping(path="edit/{id}")
    public String editProject(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("project", projectRepository.findOne(id));
        return "project/editproject";
    }

    @PostMapping(path="/edit")
    public String editProject(@ModelAttribute("project") ProjectDto projectDto) throws IOException{
        projectService.updateProject(projectDto);
        return "redirect:/project";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable Integer id) {
        projectService.remove(id);
        return "redirect:/";
    }

    @ModelAttribute("allUsers")
    public List<User> populateStatuses() {
        return userRepository.findAll();
    }

}
