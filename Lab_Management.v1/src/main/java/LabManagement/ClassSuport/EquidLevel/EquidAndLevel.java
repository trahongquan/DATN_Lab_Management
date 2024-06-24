package LabManagement.ClassSuport.EquidLevel;

public class EquidAndLevel {
    private int equidid;
    private String level;
    private String usingdate;

    public EquidAndLevel() {
    }

    public EquidAndLevel(int equidid, String level, String usingdate) {
        this.equidid = equidid;
        this.level = level;
        this.usingdate = usingdate;
    }

    public int getEquidid() {
        return equidid;
    }

    public void setEquidid(int equidid) {
        this.equidid = equidid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsingdate() {
        return usingdate;
    }

    public void setUsingdate(String usingdate) {
        this.usingdate = usingdate;
    }

    @Override
    public String toString() {
        return "EquidAndLevel{" +
                "equidid=" + equidid +
                ", level='" + level + '\'' +
                ", usingdate='" + usingdate + '\'' +
                '}';
    }
}
