package LabManagement.entity;

import LabManagement.ClassSuport.ToList;
import LabManagement.service.EquipmentService.EquipmentService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "series_fixed")
    private String seriesFixed;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "is_deleted", nullable = false)
    private int isDeleted;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "levels", nullable = false)
    private String levels;

    public Equipment() {
    }

    public Equipment(String name, String series, String seriesFixed, String description, int quantity, int isDeleted, String origin, String levels) {
        this.name = name;
        this.series = series;
        this.seriesFixed = seriesFixed;
        this.description = description;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
        this.origin = origin;
        this.levels = levels;
    }

    public List<String> getSeriesAsList() {
        return new ToList().StringToList(series);
    }

    public List<String> getSeriesFixedAsList() {
        return new ToList().StringToList(seriesFixed);
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

    public String getSeriesFixed() {
        return seriesFixed;
    }

    public void setSeriesFixed(String seriesFixed) {
        this.seriesFixed = seriesFixed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return new ToList().StringToList(series).size();
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<String> getLevelList() {
        return new ToList().StringLevelsToList(levels);
    }

    public void setLevelList(String levels) {
        this.levels = levels;
    }

    public void setEquipmentSeries(String equipmentSeries) {
        this.series = equipmentSeries;
    }

    public List<String> getEquipmentSerieList() {
        return new ToList().StringToList(series);
    }

    public List<String> getEquipmentSerieFixedList() {
        return new ToList().StringToList(seriesFixed);
    }


    public void AddSeri(EquipmentService equipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(series);
        seriesList.add(seri);
        this.setEquipmentSeries(seriesList.toString());
        equipmentService.updateEquipment(this);
    }
    public String DelSeri(EquipmentService equipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(series);
        for (int i = 0; i < seriesList.size(); i++) {
            if(seriesList.get(i).equals(seri)){
                seriesList.remove(seri);
                return this.getLevelList().get(i);
            }
        }
        this.setEquipmentSeries(seriesList.toString());
        equipmentService.updateEquipment(this);
        return "";
    }
    public void AddSeriFixed(EquipmentService equipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(seriesFixed);
        seriesList.add(seri);
        this.setEquipmentSeries(seriesList.toString());
        equipmentService.updateEquipment(this);
    }
    public void DelSeriFixed(EquipmentService equipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(seriesFixed);
        seriesList.remove(seri);
        this.setEquipmentSeries(seriesList.toString());
        equipmentService.updateEquipment(this);
    }

    public void AddLevel(EquipmentService equipmentService, String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.add(level);
        this.setLevelList(levelList.toString());
        equipmentService.updateEquipment(this);
    }
    public void DelLevel(EquipmentService equipmentService, String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.remove(level);
        this.setLevelList(levelList.toString());
        equipmentService.updateEquipment(this);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", series='" + series + '\'' +
                ", seriesFixed='" + seriesFixed + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", isDeleted=" + isDeleted +
                ", origin='" + origin + '\'' +
                ", levels='" + levels + '\'' +
                '}';
    }
}

