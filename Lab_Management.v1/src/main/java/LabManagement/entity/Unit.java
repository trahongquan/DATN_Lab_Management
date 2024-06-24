package LabManagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "Units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    public Unit() {
    }

    public Unit(String unitName) {
        this.unitName = unitName;
    }

    public int getId() {
        return id;
    }

    public String getUnitName() {
        return unitName;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", unitName='" + unitName + '\'' +
                '}';
    }
}
