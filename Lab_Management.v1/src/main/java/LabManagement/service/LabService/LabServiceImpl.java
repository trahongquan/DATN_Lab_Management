package LabManagement.service.LabService;

import LabManagement.entity.Lab;
import LabManagement.dao.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Lab> getAllLabsOnLine(){
        return labRepository.findAll().stream().filter(lab -> lab.getIsDelete()==0).collect(Collectors.toList());
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

    @Override
    public List<Lab> findAllByLabNameContainingOrLocationContainingOrAndManagingUnitContaining(String st1, String st2, String st3){
        return labRepository.findAllByLabNameContainingOrLocationContainingOrAndManagingUnitContaining(st1, st2, st3);
    }

    @Override
    public List<Lab> findAllByLabManagemetId(int id){
        return labRepository.findAllByLabManagemetId(id);
    }
}

