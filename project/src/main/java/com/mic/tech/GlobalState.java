package com.mic.tech;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class GlobalState {
    boolean isRuning=true;
    private String userName=null;
    boolean isRuning(){
        return isRuning;
    }
    String getUserName(){
        return userName;
    }
    Role getUserRole(){
        return AbstractAuthenticatedAction.currentRole;
    }
    void setState(){
        isRuning=false;
    }
    void setUserName(String userName){
        this.userName=userName;
    }
    void setRole(Role role){
        AbstractAuthenticatedAction.currentRole=role;
    }
    void setIsAuthenticated(boolean isAuthenticated){
        AbstractAuthenticatedAction.isAuthenticated=isAuthenticated;
    }
}
