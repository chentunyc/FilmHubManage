package com.mic.tech.action.userAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;

public class LogoutAction extends AbstractAuthenticatedAction {
    public LogoutAction(GlobalState state){
        this.state=state;
    }
    private GlobalState state=null;
    public String getActionName(){
        return "LOGOUT";
    }
    public String getDescription(){
        return "你可以退出账号 everyone";
    }
    public void perform(){
            state.setUserName(null);
            state.setRole(null);
            state.setIsAuthenticated(false);
            System.out.println("退出成功");
    }
}
