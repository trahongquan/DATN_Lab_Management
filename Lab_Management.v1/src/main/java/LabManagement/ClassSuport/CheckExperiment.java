package LabManagement.ClassSuport;

/** Hỗ trợ kiểm tra Experiment đã có trong danh sách chưa */

public class CheckExperiment {
    private int id;
    private boolean aBoolean;

    public CheckExperiment(int id, boolean aBoolean) {
        this.id = id;
        this.aBoolean = aBoolean;
    }

    @Override
    public String toString() {
        return "CheckExperiment{" +
                "id=" + id +
                ", aBoolean=" + aBoolean +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
