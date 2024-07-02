package LabManagement.entity;


import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "content")
public class Content implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reservationist_id", nullable = false)
    private int reservationistId;

    @Column(name = "experiment_type", nullable = false)
    private int experimentType;

    @Column(name = "experiment_report", nullable = false)
    private int experimentReport;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "amount_of_people", nullable = false)
    private int amountOfPeople;

    @Column(name = "list_id_Participants", nullable = true)
    private String listIdParticipants;

    @Column(name = "lesson", nullable = true)
    private int lesson;

    @Column(name = "fileName", nullable = true)
    private String fileName;

    public Content() {
    }

    public Content(String name, int reservationistId, int experimentType, int experimentReport, int lesson, String className, int amountOfPeople, String listIdParticipants) {
        this.name = name;
        this.reservationistId = reservationistId;
        this.experimentType = experimentType;
        this.experimentReport = experimentReport;
        this.className = className;
        this.amountOfPeople = amountOfPeople;
        this.listIdParticipants = listIdParticipants;
        this.lesson = lesson;
    }
    public Content(String name, int reservationistId, int experimentType, int experimentReport, String className, int amountOfPeople, String listIdParticipants) {
        this.name = name;
        this.reservationistId = reservationistId;
        this.experimentType = experimentType;
        this.experimentReport = experimentReport;
        this.className = className;
        this.amountOfPeople = amountOfPeople;
        this.listIdParticipants = listIdParticipants;
    }

    public Content(String name, int reservationistId, int experimentType, int experimentReport, String className, int amountOfPeople, String listIdParticipants, int lesson, String fileName) {
        this.name = name;
        this.reservationistId = reservationistId;
        this.experimentType = experimentType;
        this.experimentReport = experimentReport;
        this.className = className;
        this.amountOfPeople = amountOfPeople;
        this.listIdParticipants = listIdParticipants;
        this.lesson = lesson;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reservationistId=" + reservationistId +
                ", experimentType=" + experimentType +
                ", experimentReport=" + experimentReport +
                ", lesson=" + lesson +
                ", fileName=" + fileName +
                ", className='" + className + '\'' +
                ", amountOfPeople=" + amountOfPeople +
                ", listIdParticipants='" + listIdParticipants + '\'' +
                '}';
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

    public int getReservationistId() {
        return reservationistId;
    }

    public void setReservationistId(int reservationistId) {
        this.reservationistId = reservationistId;
    }

    public int getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(int experimentType) {
        this.experimentType = experimentType;
    }

    public int getExperimentReport() {
        return experimentReport;
    }

    public void setExperimentReport(int experimentReport) {
        this.experimentReport = experimentReport;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public String getListIdParticipants() {
        return listIdParticipants;
    }

    public void setListIdParticipants(String listIdParticipants) {
        this.listIdParticipants = listIdParticipants;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
