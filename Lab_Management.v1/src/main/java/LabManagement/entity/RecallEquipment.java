package LabManagement.entity;

import LabManagement.ClassSuport.ToList;
import LabManagement.service.EquipmentService.EquipmentService;
import LabManagement.service.RecallEquipmentService.EquipmentService.RecallEquipmentService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recall_equipment")
public class RecallEquipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "equipID", nullable = false)
    private int equipID;

    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "levels", nullable = false)
    private String levels;

    @Column(name = "recall_date", nullable = false)
    private String recallDate;

    @Column(name = "note", nullable = false)
    private String note;

    public RecallEquipment() {
    }
    public RecallEquipment(int id) {
        this.id = id;
    }

    public RecallEquipment(int equipID, String series, String levels, String recallDate, String note) {
        this.equipID = equipID;
        this.series = series;
        this.levels = levels;
        this.recallDate = recallDate;
        this.note = note;
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

    public void AddSeri(RecallEquipmentService recallEquipmentService, String seri){
        List<String> seriesList = new ToList().StringToList(series);
        seriesList.add(seri);
        this.setSeries(seriesList.toString());
        recallEquipmentService.updateRecallEquipment(this);
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
        recallEquipmentService.updateRecallEquipment(this);
        return -1;
    }

    public void AddLevel(RecallEquipmentService recallEquipmentService, String level){
        List<String> levelList = new ToList().StringLevelsToList(levels);
        levelList.add(level);
        this.setLevels(levelList.toString());
        recallEquipmentService.updateRecallEquipment(this);
    }
    public void DelLevel(RecallEquipmentService recallEquipmentService, String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.remove(level);
        this.setLevels(levelList.toString());
        recallEquipmentService.updateRecallEquipment(this);
    }

    public void AddRecallEquip(RecallEquipmentService recallEquipmentService, String using1){
        List<String> usingList = new ToList().StringToList(recallDate);
        usingList.add(using1);
        this.setRecallDate(usingList.toString());
        recallEquipmentService.updateRecallEquipment(this);
    }
    public void DelUsingRecallEquip(RecallEquipmentService recallEquipmentService, String using1){
        List<String> usingList = new ToList().StringToList(recallDate);
        usingList.remove(using1);
        this.setRecallDate(usingList.toString());
        recallEquipmentService.updateRecallEquipment(this);
    }

    public void AddNote(RecallEquipmentService recallEquipmentService, String note1){
        List<String> Notes = new ToList().StringLevelsToList(note);
        Notes.add(note1);
        this.setNote(Notes.toString());
        recallEquipmentService.updateRecallEquipment(this);
    }
    public void DelNote(RecallEquipmentService recallEquipmentService, String note1){
        List<String> Notes = new ToList().StringLevelsToList(note);
        Notes.remove(note1);
        this.setNote(Notes.toString());
        recallEquipmentService.updateRecallEquipment(this);
    }

    @Override
    public String toString() {
        return "RecallEquipment{" +
                "id=" + id +
                ", equipID=" + equipID +
                ", series='" + series + '\'' +
                ", levels='" + levels + '\'' +
                ", recallDate='" + recallDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

