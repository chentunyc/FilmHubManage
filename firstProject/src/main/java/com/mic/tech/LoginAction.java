package com.mic.tech;
import java.util.Scanner;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class LoginAction extends AbstractAction{
    Scanner scanner=null;
    GlobalState state=null;
    LoginAction(Scanner scanner,GlobalState state){
        this.scanner=scanner;
        this.state=state;
    }
    String getActionName(){
        return "LOGIN";
    }
    String getDescription(){
        return "你可以登录你的账号";
    }
    void run() {
        System.out.println("请输入用户名");
        String userName=scanner.nextLine();
        System.out.println("请输入密码");
        String password=scanner.nextLine();
        Role role=login(userName,password);
        if (role!=null){
            state.setUserName(userName);
            state.setRole(role);
            state.setIsAuthenticated(true);
            System.out.println("登录成功");
        }
        else{
            System.out.println("登录失败");
        }
    }
    private Role login(String userName,String password){
        if(userName.equals("admin")&&password.equals("ynuinfo#777")) {
            return Role.ADMINISTRATOR;
        }
        else{
            return null;
        }
    }
}
