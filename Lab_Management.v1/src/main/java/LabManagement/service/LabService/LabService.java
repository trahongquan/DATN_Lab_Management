package LabManagement.service.LabService;

import LabManagement.entity.Lab;

import java.util.List;

public interface LabService {

    Lab findByLabId(int labId);

    List<Lab> getAllLabs();

    Lab createLab(Lab lab);

    void updateLab(Lab lab);

    void deleteLab(int labId);
}

