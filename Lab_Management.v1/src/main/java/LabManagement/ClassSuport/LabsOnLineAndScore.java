package LabManagement.ClassSuport;

import LabManagement.entity.Lab;

import java.util.ArrayList;
import java.util.List;

public class LabsOnLineAndScore {
    private Lab lab;
    private Double score;
    private List<String> typeName;
    private List<Integer> quantity;
    private Integer hour;
    private List<Double> scores;

    public LabsOnLineAndScore(Lab lab, double score) {
        this.lab = lab;
        this.score = score;
    }

    public LabsOnLineAndScore(Lab lab, Double score, List<String> typeName, List<Integer> quantity, int hour, List<Double> scores) {
        this.lab = lab;
        this.score = score;
        this.typeName = typeName;
        this.quantity = quantity;
        this.hour = hour;
        this.scores = scores;
    }

    public LabsOnLineAndScore() {
        this.score = new Double(0);
        typeName = new ArrayList<>();
        quantity = new ArrayList<>();
        hour = new Integer(0);
        scores = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "LabsOnLineAndScore{" +
                "lab=" + lab +
                ", score=" + score +
                ", typeName=" + typeName +
                ", quantity=" + quantity +
                ", hour=" + hour +
                ", scores=" + scores +
                '}';
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getTypeName() {
        return typeName;
    }

    public void setTypeName(List<String> typeName) {
        this.typeName = typeName;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    public void addDetailScore(String typeName, int quantity, double score) {
        this.typeName.add(typeName);
        this.quantity.add(quantity);
        this.scores.add(score);
    }

    public void increaseQuantityAtIndex(int index) {
        int currentValue = quantity.get(index);
        quantity.set(index, currentValue + 1);
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
