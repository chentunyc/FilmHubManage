package com.mic.tech;

import java.util.Scanner;

public class ChangeUserEmailAction extends AbstractAuthenticatedAction{
    UserService userService=null;
    GlobalState state =null;
    Scanner scanner=null;
    ChangeUserEmailAction(UserService userService, GlobalState state, Scanner scanner){
        this.state = state;
        this.userService=userService;
        this.scanner=scanner;
    }

    public void perform(){
        AbstractAuthenticatedAction.Role currentRole= state.getUserRole();
        if(currentRole== AbstractAuthenticatedAction.Role.ADMINISTRATOR){
            super.print("请输入需要修改的用户名:");
            String userName=scanner.nextLine();
            User user = userService.getUserByUserName(userName);
            if (user == null) {
                super.println("没有找到该用户");
            } else {
                super.print("请输入新的用户邮箱");
                String email = scanner.nextLine();
                user.setEmail(email);
                this.userService.updateUser(user);
                super.println("已经成功更改用户邮箱");
            }
        }
        else{
            super.println("你不是管理员");
        }
    }
    public String getDescription(){
        return "你可以更改用户邮箱";
    }
    public String getActionName(){
        return "CHANGE_USER_EMAIL";
    }
}
