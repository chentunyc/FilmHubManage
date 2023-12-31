package com.mic.tech.action.userAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

import java.util.Scanner;

public class ChangeUserTelephoneNumberAction extends AbstractAuthenticatedAction {
    private UserService userService=null;
    private GlobalState state =null;
    private Scanner scanner=null;
    public ChangeUserTelephoneNumberAction(UserService userService, GlobalState state, Scanner scanner){
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
                super.print("请输入新的用户手机号");
                String telephoneNumber = scanner.nextLine();
                user.setTelephoneNumber(telephoneNumber);
                user.setTelephoneNumber(telephoneNumber);
                this.userService.updateUser(user);
                super.println("已经成功更改用户手机号");
            }
        }
        else{
            super.println("你不是管理员");
        }
    }
    public String getDescription(){
        return "你可以更改用户手机号";
    }
    public String getActionName(){
        return "CHANGE_USER_TELEPHONE_NUMBER";
    }
}
