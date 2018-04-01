package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.IssueDto;
import by.test.sindalouski.issuetracker.entity.*;
import by.test.sindalouski.issuetracker.repository.*;
import by.test.sindalouski.issuetracker.service.IssueService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    private IssueRepository issueRepository;
    private UserRepository userRepository;
    private String filesPath;
    private RestTemplate restTemplate;
    private JdbcTemplate jdbcTemplate;
    private StatusRepository statusRepository;
    private ProjectRepository projectRepository;

    public IssueServiceImpl(IssueRepository issueRepository,
                            UserRepository userRepository,
                            RestTemplate restTemplate,
                            JdbcTemplate jdbcTemplate,
                            StatusRepository statusRepository,
                            ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.statusRepository = statusRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Issue> listIssues() {
            return issueRepository.findAll();
       
    }

    @Override
    public void addIssue(IssueDto issueDto) throws IOException {

        Issue issue = new Issue();
        Status status = statusRepository.findOne(issueDto.getStatusId());
        Project project = projectRepository.findOne(issueDto.getProjectId());

        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username);

        issue.setSummary(issueDto.getSummary());
        issue.setDescription(issueDto.getDescription());
        issue.setStatus(status);
        issue.setProject(project);
        issue.setCreatedBy(currentUser);
        issue.setCreateDate(LocalDateTime.now());
        User assignee = userRepository.findOne(issueDto.getAssigneeUserId());
        issue.setAssignee(assignee);

        issueRepository.save(issue);

//        Files.write(Paths.get(filesPath, issue.getId().toString()),
//                issueDto.getFile().getBytes());
    }

    @Override
    public void updateIssue(IssueDto issueDto) {
        Issue issue = issueRepository.findOne(issueDto.getId());
        Status status = statusRepository.findOne(issueDto.getStatusId());
        Project project = projectRepository.findOne(issueDto.getProjectId());

        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username);

        User assignee = userRepository.findOne((issueDto.getAssigneeUserId()));
        issue.setSummary(issueDto.getSummary());
        issue.setDescription(issueDto.getDescription());
        issue.setStatus(status);
        issue.setProject(project);
        issue.setAssignee(assignee);
        issue.setModifiedBy(currentUser);
        issue.setModifyDate(LocalDateTime.now());
        
        issueRepository.save(issue);
    }
    @Override
    public void remove(Integer id) {
        issueRepository.delete(id);
    }
}
