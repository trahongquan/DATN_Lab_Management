package LabManagement.dto;

import LabManagement.entity.Authority;
import LabManagement.entity.People;
import LabManagement.entity.Users;

public class PeopleDTO {

    private int id;
    private String name;
    private String rank;
    private String unit;
    private long militaryNumber;
    private String contact;
    private int isDelete;

    private Users user;
    private Authority authority;

    public PeopleDTO() {
    }

    public PeopleDTO(People people, Users user, Authority authority) {
        this.id = people.getId();
        this.name = people.getName();
        this.rank = people.getRank();
        this.unit = people.getUnit();
        this.militaryNumber = people.getMilitaryNumber();
        this.contact = people.getContact();
        this.isDelete = people.getIsDelete();
        this.user = user;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "PeopleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", unit='" + unit + '\'' +
                ", militaryNumber=" + militaryNumber +
                ", contact='" + contact + '\'' +
                ", isDelete=" + isDelete +
                ", user=" + user +
                ", authority=" + authority +
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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
