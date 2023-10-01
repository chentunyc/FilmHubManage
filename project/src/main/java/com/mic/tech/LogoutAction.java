package com.mic.tech;
public class LogoutAction extends AbstractAuthenticatedAction{
    LogoutAction(GlobalState state){
        this.state=state;
    }
    GlobalState state=null;
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
