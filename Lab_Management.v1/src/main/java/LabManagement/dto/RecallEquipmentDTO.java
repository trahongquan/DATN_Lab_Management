package LabManagement.dto;

import LabManagement.ClassSuport.ToList;
import LabManagement.entity.Equipment;
import LabManagement.entity.RecallEquipment;
import LabManagement.service.RecallEquipmentService.EquipmentService.RecallEquipmentService;

import java.util.List;

public class RecallEquipmentDTO {
    private int id;
    private int equipID;
    private String series;
    private String levels;
    private String recallDate;
    private String note;

    private Equipment equipment;
    private List<String> serieList;
    private List<String> levelList;
    private List<String> recallDateList;
    private List<String> noteList;

    public RecallEquipmentDTO() {
    }

    public RecallEquipmentDTO(Equipment equipment, RecallEquipment recallEquipment) {
        this.id = recallEquipment.getId();
        this.equipID = recallEquipment.getEquipID();
        this.series = recallEquipment.getSeries();
        this.levels = recallEquipment.getLevels();
        this.recallDate = recallEquipment.getRecallDate();
        this.note = recallEquipment.getNote();
        this.equipment = equipment;
        this.serieList = this.getSeriList();
        this.levelList = this.getLevelList();
        this.recallDateList = this.getRecallDateList();
        this.noteList = this.getNoteList();
    }

    public List<String> getSeriList(){
        return new ToList().StringToList(series);
    }

    public List<String> getLevelList(){
        return new ToList().StringLevelsToList(levels);
    }

    public List<String> getRecallDateList(){
        return new ToList().StringUsingToList(recallDate);
    }

    public List<String> getNoteList(){
        return new ToList().StringLevelsToList(note);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipID() {
        return equipID;
    }

    public void setEquipID(int equipID) {
        this.equipID = equipID;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(String recallDate) {
        this.recallDate = recallDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public List<String> getSerieList() {
        return serieList;
    }

    public void setSerieList(List<String> serieList) {
        this.serieList = serieList;
    }

    public void setLevelList(List<String> levelList) {
        this.levelList = levelList;
    }

    public void setRecallDateList(List<String> recallDateList) {
        this.recallDateList = recallDateList;
    }

    public void AddSeri(RecallEquipmentService recallEquipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(series);
        seriesList.add(seri);
        this.setSeries(seriesList.toString());
    }
    public int DelSeri(RecallEquipmentService recallEquipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(series);
        for (int i = 0; i < seriesList.size(); i++) {
            if(seriesList.get(i).equals(seri)){
                seriesList.remove(seri);
                return i;
            }
        }
        this.setSeries(seriesList.toString());
        return -1;
    }

    public void AddLevel(String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.add(level);
        this.setLevels(levelList.toString());
    }
    public void DelLevel(String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.remove(level);
        this.setLevels(levelList.toString());
    }

    public void AddRecallEquip(String using1){
        List<String> usingList = new ToList().StringToList(recallDate);
        usingList.add(using1);
        this.setRecallDate(usingList.toString());
    }
    public void DelUsingRecallEquip(String using1){
        List<String> usingList = new ToList().StringToList(recallDate);
        usingList.remove(using1);
        this.setRecallDate(usingList.toString());
    }
}
