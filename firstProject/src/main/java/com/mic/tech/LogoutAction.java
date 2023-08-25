package com.mic.tech;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class LogoutAction extends AbstractAction{
    LogoutAction(GlobalState state){
        this.state=state;
    }
    GlobalState state=null;
    String getActionName(){
        return "LOGOUT";
    }
    String getDescription(){
        return "你可以退出账号";
    }
    void run(){
            state.setUserName(null);
            state.setRole(null);
            state.setIsAuthenticated(false);
            System.out.println("退出成功");
    }
}
