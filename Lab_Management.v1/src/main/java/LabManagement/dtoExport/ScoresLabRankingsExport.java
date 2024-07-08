package LabManagement.dtoExport;

public class ScoresLabRankingsExport {
    private int STT;
    private String Rank;
    private String labName;
    private Double totalScore;

    public ScoresLabRankingsExport(int STT, String rank, String labName, Double totalScore) {
        this.STT = STT;
        Rank = rank;
        this.labName = labName;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "ScoresLabRankingsExport{" +
                "STT=" + STT +
                ", Rank='" + Rank + '\'' +
                ", labName='" + labName + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
}
