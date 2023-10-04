package com.mic.tech;

import java.util.Scanner;

public class ChangeUserPasswordAction extends AbstractAuthenticatedAction{
    private UserService userService=null;
    private GlobalState state =null;
    private Scanner scanner=null;
    private String userName;
    private User user;
    private String password;
    ChangeUserPasswordAction(UserService userService, GlobalState state, Scanner scanner){
        this.state = state;
        this.userService=userService;
        this.scanner=scanner;
    }

    public void perform(){
        Role currentRole= state.getUserRole();
        super.print("请输入需要修改的用户名:");
        userName=scanner.nextLine();
        user = userService.getUserByUserName(userName);
        if (user == null) {
            super.println("没有找到该用户");
        } else {
            if(state.getUserName().equals(userName)){
                changePassword();
            }else if (currentRole==Role.ADMINISTRATOR) {
                changePassword();
            }else if (currentRole==Role.MANAGER){
                Role customer=userService.getUserByUserName(userName).getRole();
                if(customer==Role.BRONZE_CUSTOMER||customer==Role.SILVER_CUSTOMER||customer==Role.GOLD_CUSTOMER){
                    changePassword();
                }
            }
        }
    }
    void changePassword(){
        super.print("请输入新的密码");
        password = scanner.nextLine();
        this.user.setPassword(this.password);
        this.userService.updateUser(this.user);
        super.println("已经成功更改密码");
    }
    public String getDescription(){
        return "你可以更改用户密码";
    }
    public String getActionName(){
        return "CHANGE_USER_PASSWORD";
    }
}
