package com.mic.tech;

import java.util.Scanner;

public class ChangeUserPasswordAction extends AbstractAuthenticatedAction{
    UserService userService=null;
    GlobalState state =null;
    Scanner scanner=null;
    ChangeUserPasswordAction(UserService userService, GlobalState state, Scanner scanner){
        this.state = state;
        this.userService=userService;
        this.scanner=scanner;
    }

    public void perform(){
        Role currentRole= state.getUserRole();
        super.print("请输入新的密码");
        String password=scanner.nextLine();
        if(currentRole==Role.ADMINISTRATOR){
            state.setAdministratorPassword(password);
            super.println("已经成功更改密码");
        }
        else{
            User user=userService.getUserByUserName(state.getUserName());
            user.setPassword(password);
            userService.updateUser(user);
            super.println("已经成功更改密码");
        }
    }
    public String getDescription(){
        return "你可以更改用户密码";
    }
    public String getActionName(){
        return "CHANGE_USER_PASSWORD";
    }
}
