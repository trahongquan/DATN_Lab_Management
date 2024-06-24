package LabManagement.service.unitService;

import LabManagement.entity.Unit;

import java.util.List;

public interface UnitService {
    Unit saveUnit(Unit unit);

    Unit getUnitById(int id);

    List<Unit> getAllUnits();
}
