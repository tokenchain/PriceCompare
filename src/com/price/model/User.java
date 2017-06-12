package com.price.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "user")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    /*用户激活状态
    * 0代表未激活
    * 1代表激活
    * */
    private byte state;
    /*邮箱激活码*/
    private String active_code;

    public User() {}

    public User(String username, String password, String email, byte state, String active_code) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.state = state;
        this.active_code = active_code;
    }

    public User(int id, String username, String password, String email, byte state, String active_code) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.state = state;
        this.active_code = active_code;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getActive_code() {
        return active_code;
    }

    public void setActive_code(String active_code) {
        this.active_code = active_code;
    }
}
