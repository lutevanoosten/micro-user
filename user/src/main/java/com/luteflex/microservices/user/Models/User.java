package com.luteflex.microservices.user.Models;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String avatar;
    private String name;
    private String subscription;
    private String role;

    public User(){}


    public User(int Id, String Email, String Password, String Avatar, String Name, String Subscription, String Role) {


        id = Id;
        email = Email;
        password = Password;
        avatar = Avatar;
        name = Name;
        subscription = Subscription;
        role = Role;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAvatar() { return avatar; }
    public String getName() { return name; }
    public String getSubscription() { return subscription; }
    public String getRole() { return role; }

}
