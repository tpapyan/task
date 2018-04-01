package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.IssueDto;
import by.test.sindalouski.issuetracker.entity.Issue;

import java.io.IOException;
import java.util.List;

public interface IssueService {
    List<Issue> listIssues();

    void addIssue(IssueDto issueDto) throws IOException;

    void remove(Integer id);

    void updateIssue(IssueDto issueDto);

  }
