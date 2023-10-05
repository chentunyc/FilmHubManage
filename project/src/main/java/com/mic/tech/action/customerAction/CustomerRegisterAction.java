package com.mic.tech.action.customerAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

import java.util.List;
import java.util.Scanner;

public class CustomerRegisterAction extends AbstractAuthenticatedAction {
    private UserService userService = null;
    private Scanner scanner = null;
    private GlobalState state = null;

    public CustomerRegisterAction(GlobalState state, UserService userService, Scanner scanner) {
        this.state = state;
        this.userService = userService;
        this.scanner = scanner;
    }

    public String getDescription() {
        return "你可以增加用户 only admin";
    }

    public String getActionName() {
        return "CUSTOMER_REGISTER";
    }

    public void perform() {
        if (state.getUserRole() == Role.ADMINISTRATOR) {
            User user = new User();
            List<User> list=userService.getAllUsers();
            String userName=null;
            String password=null;
            String password1=null;
            String rule="[a-z A-Z| 0-9|\\p{Punct}]+";
            boolean verifyEmail=false;
            boolean verifyTelephoneNumber=false;
            super.print("请输入用户名,用户名需要大于或等于5个字符：");
            userName=scanner.nextLine();
            while (userName.length()<5||userService.getUserByUserName(userName)!=null) {
                super.println("用户名不能重复");
                super.print("请输入用户名,用户名需要大于或等于5个字符：");
                userName=scanner.nextLine();
            }
            user.setUsername(userName);
            super.println("密码长度需要大于等于八位，两次输入不能重复，只能是数字字母和符号");
            super.print("请输入密码：");
            password = scanner.nextLine();
            super.print("请再次输入密码：");
            password1 = scanner.nextLine();
            while (!password.equals(password1)||!password.matches(rule)||password.length()<8) {
                super.println("不符合规范");
                super.print("请输入密码：");
                password = scanner.nextLine();
                super.print("请再次输入密码：");
                password1 = scanner.nextLine();
            }
            user.setPassword(password);
            user.setRole(Role.BRONZE_CUSTOMER);

            String email=null;
            super.print("请输入邮箱：");
            email=scanner.nextLine();
            while (!verifyEmail) {
                for (int i = 0; i < list.size(); i++) {
                    if(email.equals(userService.getUserByUserId(i).getEmail())){
                        super.print("邮箱重复，请再次请输入邮箱：");
                        email=scanner.nextLine();
                    }
                    if(i==list.size()-1&&email!=(userService.getUserByUserId(i).getEmail()))
                        verifyEmail=true;
                }
                user.setEmail(email);
            }

            String telephoneNumber=null;
            super.print("请输入手机号码：");
            telephoneNumber=scanner.nextLine();
            while (!verifyTelephoneNumber) {
                for (int i = 0; i < list.size(); i++) {
                    if(telephoneNumber.equals(userService.getUserByUserId(i).getTelephoneNumber())){
                        super.print("手机号码重复，请再次请输入手机号码：");
                        telephoneNumber=scanner.nextLine();
                    }
                    if(i==list.size()-1&&!telephoneNumber.equals(userService.getUserByUserId(i).getTelephoneNumber()))
                        verifyTelephoneNumber=true;
                }
                user.setTelephoneNumber(telephoneNumber);
            }

            super.print("请输入注册时间：");
            user.setregistrationTime(scanner.nextInt());

            user.setId(userService.getAllUsers().size());
            user.setLock("false");
            userService.addUser(user);
        } else
            super.println("你不是管理员无法执行此操作");
    }
}