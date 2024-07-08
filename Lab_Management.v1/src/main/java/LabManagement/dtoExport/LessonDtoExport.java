package LabManagement.dtoExport;

import LabManagement.entity.Lab;

public class LessonDtoExport {

    private int id;
    private String labName;
    private String noLesson; /** Mã số bài thí nghiệm */
    private String name;
    private String module; /** Thuộc Học phần môn học nào*/
    private String level; /** Cấp học Đại học, Cao đẳng ...*/
    private Integer workTime;
    private String type; /** Loại bắt buộc, minh họa ...*/
    private String note; /** Đề xuất, Đang thực hiện, Đang xây dựng ...*/

    public LessonDtoExport(int id, String labName, String noLesson, String name, String module, String level, Integer workTime, String type, String note) {
        this.id = id;
        this.labName = labName;
        this.noLesson = noLesson;
        this.name = name;
        this.module = module;
        this.level = level;
        this.workTime = workTime;
        this.type = type;
        this.note = note;
    }

    @Override
    public String toString() {
        return "LessonDtoExport{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
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

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
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
