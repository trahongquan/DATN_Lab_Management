package LabManagement.dtoExport;

import java.util.List;

public class InventoryResultExport {
    private String equipName;
    private String unit;
    private List<String> seri;
    private List<String> level;
    private List<String> using;
    private int lastYear;
    private int thisYear;
    private int increase;
    private int reduce;
    private String labName;

    public InventoryResultExport(String equipName, String unit, List<String> seri, List<String> level, List<String> using, int lastYear, int thisYear, int increase, int reduce, String labName) {
        this.labName = labName;
        this.equipName = equipName;
        this.unit = unit;
        this.seri = seri;
        this.level = level;
        this.using = using;
        this.lastYear = lastYear;
        this.thisYear = thisYear;
        this.increase = increase;
        this.reduce = reduce;
    }

    @Override
    public String toString() {
        return "inventoryResultExport{" +
                "equipName='" + equipName +
                ", unit='" + unit +
                ", seri=" + seri +
                ", level=" + level +
                ", using=" + using +
                ", lastYear=" + lastYear +
                ", thisYear=" + thisYear +
                ", increase=" + increase +
                ", reduce=" + reduce +
                ", labName='" + labName +
                '}';
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getSeri() {
        return seri;
    }

    public void setSeri(List<String> seri) {
        this.seri = seri;
    }

    public List<String> getLevel() {
        return level;
    }

    public void setLevel(List<String> level) {
        this.level = level;
    }

    public List<String> getUsing() {
        return using;
    }

    public void setUsing(List<String> using) {
        this.using = using;
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

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }
}
