package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.IssueDto;
import by.test.sindalouski.issuetracker.entity.*;
import by.test.sindalouski.issuetracker.repository.*;
import by.test.sindalouski.issuetracker.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/issue")
public class IssueController {
    private IssueService issueService;
    private StatusRepository statusRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private IssueRepository issueRepository;


    public IssueController(IssueService issueService,
                           StatusRepository statusRepository,
                           ProjectRepository projectRepository,
                           UserRepository userRepository,
                           IssueRepository issueRepository) {
        this.issueService = issueService;
        this.statusRepository = statusRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
    }

    @GetMapping("/add")
    public String issueForm(Model model) {

        model.addAttribute("issue", new IssueDto());
        return "addissue";
    }

    @PostMapping("/add")
    public String addIssue(@ModelAttribute("issue") IssueDto issueDto) throws IOException {
        issueService.addIssue(issueDto);
        return "added";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable Integer id) {
        issueService.remove(id);
        return "redirect:/";
    }

    @GetMapping(path="edit/{id}")
    public String editIssue(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("issue", issueRepository.findOne(id));
        return "editissue";
    }

    @PostMapping(path="/edit")
    public String editIssue(@ModelAttribute("issue") IssueDto issueDto) throws IOException{
        issueService.updateIssue(issueDto);
        return "redirect:/";
    }


    @GetMapping(path="/guestview/{id}")
    public String guestIssueView(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("issue", issueRepository.findOne(id));
        return "guestview";
    }

    @ModelAttribute("allStatuses")
    public List<Status> populateStatuses() {
        return statusRepository.findAll();
    }

    @ModelAttribute("allProjects")
    public List<Project> populateProjects() {
        return projectRepository.findAll();
    }

    @ModelAttribute("allUsers")
    public List<User> populateUsers() {
        return userRepository.findAll();
    }

}
