package com.mic.tech.action.userAction;
import java.util.Scanner;

import com.mic.tech.AbstractAction;
import com.mic.tech.AbstractAuthenticatedAction.Role;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

public class LoginAction extends AbstractAction {
    private Scanner scanner=null;
    private GlobalState state=null;
    private UserService userService=null;

    public LoginAction(Scanner scanner,GlobalState state,UserService userService){
        this.scanner=scanner;
        this.state=state;
        this.userService=userService;
    }
    public String getActionName(){
        return "LOGIN";
    }
    public String getDescription(){
        return "你可以登录你的账号 everyone";
    }
    public void run() {
        int tryNumber=0;
        String userName=null;
        String password=null;
        boolean locked=false;
        Role role=null;
        while (!locked&&role==null){
            System.out.print("请输入用户名:");
            userName = scanner.nextLine();
            System.out.print("请输入密码:");
            password = scanner.nextLine();
            role = login(userName, User.hashPassword(password));
            tryNumber++;
            if(tryNumber==5){
                userService.getUserByUserName(userName).setLock("true");
                System.out.println("失败超过五次，账号被锁定");
                break;
            }
        }
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
        User user = this.userService.getUserByUserName(userName);
        if (user != null && user.getPassword().equals(password)&&user.getLock().equals("false")) {
            return user.getRole();
        }
        else if (user != null && user.getPassword().equals(password)&&user.getLock().equals("true")){
            System.out.println("账户已经被锁定");
            return null;
        }
        else{
            return null;
        }
    }
}
