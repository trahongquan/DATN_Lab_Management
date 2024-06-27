package LabManagement.service.ManagingUnitService;

import LabManagement.dao.ManagingUnitRepository;
import LabManagement.entity.ManagingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagingUnitServiceImpl implements ManagingUnitService {
    private final ManagingUnitRepository managingUnitRepository;

    @Autowired
    public ManagingUnitServiceImpl(ManagingUnitRepository managingUnitRepository) {
        this.managingUnitRepository = managingUnitRepository;
    }

    @Override
    public List<ManagingUnit> getAllManagingUnits() {
        return managingUnitRepository.findAll();
    }

    @Override
    public ManagingUnit getManagingUnitById(int id) {
        return managingUnitRepository.findById(id).orElse(null);
    }

    @Override
    public ManagingUnit createManagingUnit(ManagingUnit managingUnit) {
        return managingUnitRepository.save(managingUnit);
    }

    @Override
    public ManagingUnit updateManagingUnit(int id, ManagingUnit managingUnit) {
        ManagingUnit existingManagingUnit = managingUnitRepository.findById(id).orElse(null);
        if (existingManagingUnit != null) {
            existingManagingUnit.setDepartmentName(managingUnit.getDepartmentName());
            return managingUnitRepository.save(existingManagingUnit);
        }
        return null;
    }

    @Override
    public void deleteManagingUnit(int id) {
        managingUnitRepository.deleteById(id);
    }

    @Override
    public ManagingUnit findByDepartmentName(String s) {
        return managingUnitRepository.findByDepartmentName(s);
    }
    // ...
}