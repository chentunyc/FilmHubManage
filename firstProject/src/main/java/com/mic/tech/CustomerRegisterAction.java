package com.mic.tech;

import java.util.Scanner;

public class CustomerRegisterAction extends AbstractAuthenticatedAction{
    UserService userService=null;
    Scanner scanner=null;
    GlobalState state=null;
    CustomerRegisterAction(GlobalState state,UserService userService,Scanner scanner){
        this.state=state;
        this.userService=userService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "你可以增加用户";
    }
    public String getActionName() {
        return "CUSTOMER_REGISTERACTION";
    }
    void perform() {
        if(state.getUserRole()==Role.ADMINISTRATOR){
            User user=new User();
            super.print("请输入用户名：");
            user.setUsername(scanner.nextLine());
            super.print("请输入密码：");
            String password=scanner.nextLine();
            super.print("请再次输入密码：");
            String password1=scanner.nextLine();
            if(!password.equals(password1)) {
                super.println("俩次密码不一致");
            }
            else{
                user.setPassword(password);
                user.setRole(Role.CUSTOMER);
                super.print("请输入邮箱：");
                user.setEmail(scanner.nextLine());
                super.print("请输入手机号码：");
                user.setTelephoneNumber(scanner.nextInt());
                super.print("请输入注册时间：");
                user.setregistrationTime(scanner.nextInt());
                userService.addUser(user);
            }
        }
        else
            super.println("你不是管理员无法执行此操作");
    }
}
