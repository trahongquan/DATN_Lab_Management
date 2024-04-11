package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "authority", nullable = false)
    private String authority;

    public Authority() {
    }

    public Authority(int id, String username, String authority) {
        this.id = id;
        this.username = username;
        this.authority = authority;
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}


