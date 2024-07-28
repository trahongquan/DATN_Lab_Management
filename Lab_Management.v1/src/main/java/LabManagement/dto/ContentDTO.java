package LabManagement.dto;

import LabManagement.entity.*;

public class ContentDTO {
    private int id;
    private String name;
    private int reservationistId;
    private int experimentTypeId;
    private int experimentReportId;
    private String className;
    private int amountOfPeople;
    private int lessonId;
    private String listIdParticipants;
    private String fileName;

    private Lesson lesson;
    private ExperimentGroup experimentGroup;
    private ExperimentType experimentType;
    private ExperimentReport experimentReport;
    private People reservationist;

    public ContentDTO() {
    }

    public ContentDTO(Content content, People reservationist, ExperimentGroup experimentGroup, ExperimentType experimentType, ExperimentReport experimentReport, Lesson lesson) {
        this.id = content.getId();
        this.name = content.getName();
        this.reservationistId = content.getReservationistId();
        this.experimentTypeId = content.getExperimentType();
        this.experimentReportId = content.getExperimentReport();
        this.className = content.getClassName();
        this.amountOfPeople = content.getAmountOfPeople();
        this.listIdParticipants = content.getListIdParticipants();
        this.fileName = content.getFileName();
        this.reservationist = reservationist;
        this.experimentGroup = experimentGroup;
        this.experimentType = experimentType;
        this.experimentReport = experimentReport;
        this.lessonId = content.getLesson();
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "ContentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reservationistId=" + reservationistId +
                ", experimentTypeId=" + experimentTypeId +
                ", experimentReportId=" + experimentReportId +
                ", className='" + className + '\'' +
                ", amountOfPeople=" + amountOfPeople +
                ", listIdParticipants='" + listIdParticipants + '\'' +
                ", fileName='" + fileName + '\'' +
                ", experimentGroup=" + experimentGroup +
                ", experimentType=" + experimentType +
                ", experimentReport=" + experimentReport +
                ", reservationist=" + reservationist +
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

    public int getExperimentTypeId() {
        return experimentTypeId;
    }

    public void setExperimentTypeId(int experimentTypeId) {
        this.experimentTypeId = experimentTypeId;
    }

    public int getExperimentReportId() {
        return experimentReportId;
    }

    public void setExperimentReportId(int experimentReportId) {
        this.experimentReportId = experimentReportId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ExperimentGroup getExperimentGroup() {
        return experimentGroup;
    }

    public void setExperimentGroup(ExperimentGroup experimentGroup) {
        this.experimentGroup = experimentGroup;
    }

    public ExperimentType getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(ExperimentType experimentType) {
        this.experimentType = experimentType;
    }

    public ExperimentReport getExperimentReport() {
        return experimentReport;
    }

    public void setExperimentReport(ExperimentReport experimentReport) {
        this.experimentReport = experimentReport;
    }

    public People getReservationist() {
        return reservationist;
    }

    public void setReservationist(People reservationist) {
        this.reservationist = reservationist;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
