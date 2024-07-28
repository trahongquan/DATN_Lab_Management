package LabManagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "managingunit")
public class ManagingUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "department_name")
    private String departmentName;

    public ManagingUnit() {
    }
    public ManagingUnit(int id) {
        this.id = 0;
        this.departmentName = "Xem tất cả các bộ môn";
    }
    public ManagingUnit(int id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public ManagingUnit(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "ManagingUnit{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
