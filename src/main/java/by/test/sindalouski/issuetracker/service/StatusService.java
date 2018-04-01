package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Status;

import java.util.List;

public interface StatusService{

	List<Status> listStatuses();

    void updateStatus(StatusDto statusDto);
}
