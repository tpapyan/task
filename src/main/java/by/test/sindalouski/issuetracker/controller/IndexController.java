package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.entity.Issue;
import by.test.sindalouski.issuetracker.service.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    private IssueService issueService;


    public IndexController(IssueService issueService) {
        this.issueService = issueService;

    }

    @GetMapping("/")
    public String index(Model model,
                        HttpSession session) {

        List<Issue> issues = issueService.listIssues();
        model.addAttribute("issues", issues);

        return "index";
    }


}
