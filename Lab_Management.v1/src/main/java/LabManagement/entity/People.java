package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "people")
public class People implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rank", nullable = false)
    private String rank;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "military_number", nullable = false)
    private long militaryNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "is_delete", nullable = false)
    private int isDelete;

    public People() {
    }

    public People(String name, String rank, String unit, long militaryNumber, String email, String phone, int isDelete) {
        this.name = name;
        this.rank = rank;
        this.unit = unit;
        this.militaryNumber = militaryNumber;
        this.email = email;
        this.phone = phone;
        this.isDelete = isDelete;
    }
// Getters and Setters (omitted for brevity)

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getMilitaryNumber() {
        return militaryNumber;
    }

    public void setMilitaryNumber(long militaryNumber) {
        this.militaryNumber = militaryNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", unit='" + unit + '\'' +
                ", militaryNumber=" + militaryNumber +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
