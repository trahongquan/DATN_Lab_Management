package LabManagement.ClassSuport;

import LabManagement.dto.EquipmentLabDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryCompare {
    private int lastYear;
    private int thisYear;
    private int increase;
    private int reduce;

    public InventoryCompare() {
    }

    public InventoryCompare(int lastYear, int thisYear, int increase, int reduce) {
        this.lastYear = lastYear;
        this.thisYear = thisYear;
        this.increase = increase;
        this.reduce = reduce;
    }

    @Override
    public String toString() {
        return "InventoryCompare{" +
                "lastYear=" + lastYear +
                ", thisYear=" + thisYear +
                ", increase=" + increase +
                ", reduce=" + reduce +
                '}';
    }

    public int getLastYear() {
        return lastYear;
    }

    public void setLastYear(int lastYear) {
        this.lastYear = lastYear;
    }

    public int getThisYear() {
        return thisYear;
    }

    public void setThisYear(int thisYear) {
        this.thisYear = thisYear;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public int getReduce() {
        return reduce;
    }

    public void setReduce(int reduce) {
        this.reduce = reduce;
    }

}