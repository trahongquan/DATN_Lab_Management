package LabManagement.ClassSuport.EquidLevel;

public class EquidAndLevel {
    private int equidid;
    private String level;

    public EquidAndLevel() {
    }

    public EquidAndLevel(int equidid, String level) {
        this.equidid = equidid;
        this.level = level;
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

    @Override
    public String toString() {
        return "EquidAndLevel{" +
                "equidid=" + equidid +
                ", level='" + level + '\'' +
                '}';
    }
}
