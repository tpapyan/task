package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Status;
import by.test.sindalouski.issuetracker.repository.StatusRepository;
import by.test.sindalouski.issuetracker.service.StatusService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/status")
@PreAuthorize("hasAuthority('ADMIN')")
public class StatusController {

    private StatusService statusService;
    private StatusRepository statusRepository;

    public StatusController(StatusService statusService,
                            StatusRepository statusRepository) {
        this.statusService = statusService;
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public String allStatuses(Model model,
                              HttpSession session) {

    	List<Status> statuses = statusService.listStatuses();
        model.addAttribute("statuses", statuses);

        return "status/statuses";
    }

    @GetMapping(path="edit/{id}")
    public String editStatus(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("status", statusRepository.findOne(id));
        return "status/editstatus";
    }

    @PostMapping(path="/edit")
    public String editStatus(@ModelAttribute("status") StatusDto statusDto) throws IOException{
        statusService.updateStatus(statusDto);
        return "redirect:/status";
    }
}
