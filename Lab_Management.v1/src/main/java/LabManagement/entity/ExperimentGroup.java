package LabManagement.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity
@Table(name = "experiment_group")
public class ExperimentGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    public ExperimentGroup() {
    }

    public ExperimentGroup(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "ExperimentGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
