package LabManagement.ClassSuport;

import LabManagement.entity.Lab;

public class LabsOnLineAndScore {
    private Lab lab;
    private Double score;

    public LabsOnLineAndScore(Lab lab, double score) {
        this.lab = lab;
        this.score = score;
    }

    public LabsOnLineAndScore() {
        this.score = new Double(0);
    }

    @Override
    public String toString() {
        return "LabsOnLineAndScore{" +
                "lab=" + lab +
                ", score=" + score +
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
}
