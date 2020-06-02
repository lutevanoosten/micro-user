package com.luteflex.microservices.user.Models;

import javax.persistence.*;


public class TokenRequest {


    private int id;
    private String name;
    private String role;
    private String avatar;

    public TokenRequest(int UserId, String Name, String Role, String Avatar) {
        id = UserId;
        name = Name;
        role = Role;
        avatar = Avatar;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getAvatar() {
        return avatar;
    }

}
