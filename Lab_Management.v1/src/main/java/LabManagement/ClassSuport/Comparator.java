package LabManagement.ClassSuport;

public class Comparator {
    private int quantity;
    private String compared;

    public Comparator(int quantity, String compared) {
        this.quantity = quantity;
        this.compared = compared;
    }

    public Comparator() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCompared() {
        return compared;
    }

    public void setCompared(String compared) {
        this.compared = compared;
    }

    @Override
    public String toString() {
        return "Comparator{" +
                "quantity=" + quantity +
                ", compared='" + compared + '\'' +
                '}';
    }
}
