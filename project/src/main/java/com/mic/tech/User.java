package com.mic.tech;
import  com.mic.tech.AbstractAuthenticatedAction.Role;
public class User {
    private String username = null;
    private String password = null;
    private Role role = null;
    private int id=0;
    private int telephoneNumber=0;
    private int registrationTime=0;
    private String email=null;
    private int purchaseNumber=0;
    private int purchaseAmount=0;
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
    public void setregistrationTime(int registrationTime){this.registrationTime=registrationTime;}
    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPurchaseNumber(int purchaseNumber){
        this.purchaseNumber=purchaseNumber;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }
    public void setPurchaseAmount(int purchaseAmount){
        this.purchaseAmount=purchaseAmount;
    }
    public int getPurchaseAmount(){
        return purchaseAmount;
    }
}
