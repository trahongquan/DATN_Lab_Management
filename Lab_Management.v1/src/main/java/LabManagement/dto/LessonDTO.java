package LabManagement.dto;

import LabManagement.entity.Lab;
import LabManagement.entity.Lesson;

public class LessonDTO {
    private int id;
    private int labId;
    private String noLesson; /** Mã số bài thí nghiệm */
    private String name;
    private String module; /** Thuộc Học phần môn học nào*/
    private String level; /** Cấp học Đại học, Cao đẳng ...*/
    private Integer workTime;
    private String type; /** Loại bắt buộc, minh họa ...*/
    private String note; /** Đề xuất, Đang thực hiện, Đang xây dựng ...*/

    private Lab lab;

    public LessonDTO() {
    }

    public LessonDTO(Lesson lesson, Lab lab) {
        this.id = lesson.getId();
        this.labId = lesson.getLabId();
        this.noLesson = lesson.getNoLesson();
        this.name = lesson.getName();
        this.module = lesson.getModule();
        this.level = lesson.getLevel();
        this.workTime = lesson.getWorkTime();
        this.type = lesson.getType();
        this.note = lesson.getNote();
        this.lab = lab;
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "id=" + id +
                ", labId=" + labId +
                ", noLesson='" + noLesson + '\'' +
                ", name='" + name + '\'' +
                ", module='" + module + '\'' +
                ", level='" + level + '\'' +
                ", workTime=" + workTime +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                ", lab=" + lab +
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

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
}
