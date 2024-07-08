package LabManagement.dtoExport;

public class PeoplesExport {
    private int id;
    private String name;
    private String rank;
    private String unit;
    private long militaryNumber;
    private String contact;
    private String username;
    private String password;
    private String role;

    public PeoplesExport() {
    }

    public PeoplesExport(int id, String name, String rank, String unit, long militaryNumber, String contact, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.unit = unit;
        this.militaryNumber = militaryNumber;
        this.contact = contact;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "ManagersExport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", unit='" + unit + '\'' +
                ", militaryNumber=" + militaryNumber +
                ", contact='" + contact + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getMilitaryNumber() {
        return militaryNumber;
    }

    public void setMilitaryNumber(long militaryNumber) {
        this.militaryNumber = militaryNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
