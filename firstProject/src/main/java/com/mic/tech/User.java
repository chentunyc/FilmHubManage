package com.mic.tech;
import  com.mic.tech.AbstractAuthenticatedAction.Role;
public class User {
    private String username = null;
    private String password = null;
    private Role role = null;
    private int id;
    private int telephoneNumber;
    private String email;
    private int registrationTime;


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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getTelephoneNumber(){
        return telephoneNumber;
    }
    public void setTelephoneNumber(int telephoneNumber){
        this.telephoneNumber=telephoneNumber;
    }
    public int getRegistrationTime(){
        return registrationTime;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
