package com.mic.tech;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class GlobalState {
    boolean isRuning=true;
    private String userName=null;
    void setState(){
        isRuning=false;
    }
    boolean isRuning(){
        return isRuning;
    }
    void setUserName(String userName){
        this.userName=userName;
    }
    String getUserName(){
        return userName;
    }
    void setRole(Role role){
        AbstractAuthenticatedAction.currentRole=role;
    }
    void setIsAuthenticated(boolean isAuthenticated){
        AbstractAuthenticatedAction.isAuthenticated=isAuthenticated;
    }
}
