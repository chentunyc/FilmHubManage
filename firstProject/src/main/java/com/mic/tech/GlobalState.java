package com.mic.tech;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class GlobalState {
    boolean isRuning=true;
    private String userName=null;
    private String administratorName="admin";
    private String administratorPassword="ynuinfo#777";
    boolean isRuning(){
        return isRuning;
    }
    String getAdministratorPassword(){return administratorPassword;};
    String getAdministratorName(){return administratorName;}
    String getUserName(){
        return userName;
    }
    Role getUserRole(){
        return AbstractAuthenticatedAction.currentRole;
    }
    void setState(){
        isRuning=false;
    }
    void setAdministratorPassword(String administratorPassword){this.administratorPassword=administratorPassword;}
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
