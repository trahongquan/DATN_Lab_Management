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

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "amount_of_people", nullable = false)
    private int amountOfPeople;

    @Column(name = "list_id_Participants", nullable = false)
    private String listIdParticipants;

    public Content() {
    }

    public Content(String name, int reservationistId, int experimentType, String className, int amountOfPeople, String listIdParticipants) {
        this.name = name;
        this.reservationistId = reservationistId;
        this.experimentType = experimentType;
        this.className = className;
        this.amountOfPeople = amountOfPeople;
        this.listIdParticipants = listIdParticipants;
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

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reservationistId=" + reservationistId +
                ", experimentType=" + experimentType +
                ", className='" + className + '\'' +
                ", amountOfPeople=" + amountOfPeople +
                ", listIdParticipants='" + listIdParticipants + '\'' +
                '}';
    }
}
