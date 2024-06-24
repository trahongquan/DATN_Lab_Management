package LabManagement.service.LabService;

import LabManagement.entity.Lab;

import java.sql.Date;
import java.util.List;

public interface LabService {

    Lab findByLabId(int labId);

    List<Lab> getAllLabs();

    List<Lab> getAllLabsOnLine();

    Lab createLab(Lab lab);

    void updateLab(Lab lab);

    void deleteLab(int labId);

    List<Lab> findAllByLabNameContainingOrLocationContainingOrAndManagingUnitContaining(String st1, String st2, String st3);
}

