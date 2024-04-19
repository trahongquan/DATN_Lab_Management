package LabManagement.dto;

import LabManagement.ClassSuport.ToList;
import LabManagement.entity.Equipment;

import java.util.List;

public class EquipmentDTO {
    private int id;
    private String name;
    private String series;
    private List<String> serieList;
    private String seriesFixed;
    private List<String> serieFixedList;
    private String description;
    private int quantity;
    private int isDeleted;

    public EquipmentDTO() {
    }

    public EquipmentDTO(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.series = equipment.getSeries();
        this.serieList = new ToList().StringToList(series);
        this.seriesFixed = equipment.getSeriesFixed();
        this.serieFixedList = new ToList().StringToList(seriesFixed);
        this.description = equipment.getDescription();
        this.quantity = serieList.size();
        this.isDeleted = equipment.getIsDeleted();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public List<String> getSerieList() {
        return serieList;
    }

    public void setSerieList(List<String> serieList) {
        this.serieList = serieList;
    }

    public String getSeriesFixed() {
        return seriesFixed;
    }

    public void setSeriesFixed(String seriesFixed) {
        this.seriesFixed = seriesFixed;
    }

    public List<String> getSerieFixedList() {
        return serieFixedList;
    }

    public void setSerieFixedList(List<String> serieFixedList) {
        this.serieFixedList = serieFixedList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "EquipmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", series='" + series + '\'' +
                ", serieList=" + serieList +
                ", seriesFixed='" + seriesFixed + '\'' +
                ", serieFixedList=" + serieFixedList +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
