package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Status;
import by.test.sindalouski.issuetracker.repository.StatusRepository;
import by.test.sindalouski.issuetracker.service.StatusService;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class StatusServiceImpl implements StatusService{

    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> listStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public void updateStatus(StatusDto statusDto) {

        Status status = statusRepository.findOne(statusDto.getId());
        status.setStatusName(statusDto.getStatusName());
        statusRepository.save(status);
    }
}
