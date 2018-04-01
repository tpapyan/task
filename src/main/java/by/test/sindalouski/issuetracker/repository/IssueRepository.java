package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {


}
