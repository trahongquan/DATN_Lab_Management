package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "Username"))
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "people_id")
    private int peopleid;
//    @Column(name = "is_deleted")
//    private int is_deleted;
    @Column(name = "enabled")
    private int enabled;

    public Users() {
    }

    public Users(int id, String username, String password, int peopleid, /*int is_deleted,*/ int enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.peopleid = peopleid;
//        this.is_deleted = is_deleted;
        this.enabled = enabled;
    }

    public Users(String username, String password, int peopleid, /*int is_deleted,*/ int enabled) {
        this.username = username;
        this.password = password;
        this.peopleid = peopleid;
//        this.is_deleted = is_deleted;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPeopleid() {
        return peopleid;
    }

    public void setPeopleid(int people_id) {
        this.peopleid = people_id;
    }

//    public int getIs_deleted() {
//        return is_deleted;
//    }
//
//    public void setIs_deleted(int is_deleted) {
//        this.is_deleted = is_deleted;
//    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}

