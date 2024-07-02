package LabManagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "labid")
    private int labId;

    @Column(name = "nolesson")
    private String noLesson;

    @Column(name = "name")
    private String name;

    @Column(name = "module")
    private String module;

    @Column(name = "level")
    private String level;

    @Column(name = "worktime")
    private Integer workTime;

    @Column(name = "type")
    private String type;

    @Column(name = "note")
    private String note;

    public Lesson(int labId, String noLesson, String name, String module, String level, Integer workTime, String type, String note) {
        this.labId = labId;
        this.noLesson = noLesson;
        this.name = name;
        this.module = module;
        this.level = level;
        this.workTime = workTime;
        this.type = type;
        this.note = note;
    }

    public Lesson() {
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", labId=" + labId +
                ", noLesson='" + noLesson + '\'' +
                ", name='" + name + '\'' +
                ", module='" + module + '\'' +
                ", level='" + level + '\'' +
                ", workTime=" + workTime +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getNoLesson() {
        return noLesson;
    }

    public void setNoLesson(String noLesson) {
        this.noLesson = noLesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
