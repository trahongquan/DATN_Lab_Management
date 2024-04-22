package LabManagement.service.LabService;

import LabManagement.entity.Lab;
import LabManagement.dao.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabRepository labRepository;

    @Override
    public Lab findByLabId(int roomId) {
        return labRepository.findById(roomId).orElse(null);
    }

    @Override
    public List<Lab> getAllLabs() {
        return labRepository.findAll();
    }

    @Override
    public Lab createLab(Lab room) {
        return labRepository.save(room);
    }

    @Override
    public void updateLab(Lab room) {
        labRepository.save(room);
    }

    @Override
    public void deleteLab(int roomId) {
        Lab lab = findByLabId(roomId);
        lab.setIsDelete(1);
        updateLab(lab);
    }
}

