package com.mic.tech;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class GlobalState {
    private boolean isRuning=true;
    private String userName=null;
    public boolean getIsRuning(){
        return isRuning;
    }
    public String getUserName(){
        return userName;
    }
    public Role getUserRole(){
        return AbstractAuthenticatedAction.currentRole;
    }
    public void setIsRuning(){
        isRuning=false;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public void setRole(Role role){
        AbstractAuthenticatedAction.currentRole=role;
    }
    public void setIsAuthenticated(boolean isAuthenticated){
        AbstractAuthenticatedAction.isAuthenticated=isAuthenticated;
    }
}
