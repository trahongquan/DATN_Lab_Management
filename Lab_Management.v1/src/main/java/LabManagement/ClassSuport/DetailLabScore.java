package LabManagement.ClassSuport;

import LabManagement.entity.Score;

import java.util.List;

/** Chưa dùng tới */

public class DetailLabScore {
    private List<Integer> quantity;
    private List<String> typeName;

    public DetailLabScore(List<Integer> quantity, List<String> typeName) {
        this.quantity = quantity;
        this.typeName = typeName;
    }

    public DetailLabScore() {
    }

    @Override
    public String toString() {
        return "DetailLabScore{" +
                "quantity=" + quantity +
                ", typeName=" + typeName +
                '}';
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public List<String> getTypeName() {
        return typeName;
    }

    public void setTypeName(List<String> typeName) {
        this.typeName = typeName;
    }
}
