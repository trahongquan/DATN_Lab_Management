package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "equipment_number", nullable = false)
    private String equipmentNumber;

    @Column(name = "equipment_type", nullable = false)
    private String equipmentType;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "production_yearint", nullable = false)
    private int productionYear;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "is_deleted", nullable = false)
    private int isDeleted;

    public Equipment() {
    }

    public Equipment(String equipmentNumber, String equipmentType, String origin, int productionYear, int status, int roomId, int isDeleted) {
        this.equipmentNumber = equipmentNumber;
        this.equipmentType = equipmentType;
        this.origin = origin;
        this.productionYear = productionYear;
        this.status = status;
        this.roomId = roomId;
        this.isDeleted = isDeleted;
    }
// Getters and Setters (omitted for brevity)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", equipmentNumber='" + equipmentNumber + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", origin='" + origin + '\'' +
                ", productionYear=" + productionYear +
                ", status=" + status +
                ", roomId=" + roomId +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

