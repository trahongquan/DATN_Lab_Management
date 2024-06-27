package LabManagement.service.ManagingUnitService;

import LabManagement.entity.ManagingUnit;

import java.util.List;

public interface ManagingUnitService {
    List<ManagingUnit> getAllManagingUnits();
    ManagingUnit getManagingUnitById(int id);
    ManagingUnit createManagingUnit(ManagingUnit managingUnit);
    ManagingUnit updateManagingUnit(int id, ManagingUnit managingUnit);
    void deleteManagingUnit(int id);
    ManagingUnit findByDepartmentName(String s);
    // ...
}
