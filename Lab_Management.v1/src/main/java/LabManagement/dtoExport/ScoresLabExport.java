package LabManagement.dtoExport;

public class ScoresLabExport {
    private int STT;
    private String typeName;
    private int quantity;
    private int hour;
    private Double score;
    private Double totalScore;

    public ScoresLabExport(int STT, String typeName, int quantity, int hour, Double score, Double totalScore) {
        this.STT = STT;
        this.typeName = typeName;
        this.quantity = quantity;
        this.hour = hour;
        this.score = score;
        this.totalScore = totalScore;
    }

    public ScoresLabExport() {
    }

    @Override
    public String toString() {
        return "ScoresLabExport{" +
                "STT='" + STT + '\'' +
                "typeName='" + typeName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", hour=" + hour +
                ", score=" + score +
                ", totalScore=" + totalScore +
                '}';
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
}
