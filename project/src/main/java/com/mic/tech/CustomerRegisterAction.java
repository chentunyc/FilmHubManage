package com.mic.tech;

import java.util.List;
import java.util.Scanner;

public class CustomerRegisterAction extends AbstractAuthenticatedAction {
    UserService userService = null;
    Scanner scanner = null;
    GlobalState state = null;

    CustomerRegisterAction(GlobalState state, UserService userService, Scanner scanner) {
        this.state = state;
        this.userService = userService;
        this.scanner = scanner;
    }

    public String getDescription() {
        return "你可以增加用户";
    }

    public String getActionName() {
        return "CUSTOMER_REGISTERACTION";
    }

    void perform() {
        if (state.getUserRole() == Role.ADMINISTRATOR) {
            User user = new User();
            List<User> list=userService.getAllUsers();
            boolean verifyEmail=false;
            boolean verifyTelephoneNumber=false;
            super.print("请输入用户名：");
            user.setUsername(scanner.nextLine());
            while (list.contains(userService.getUserByUserName(user.getUsername()))) {
                super.print("用户名重复，请再次输入用户名：");
                user.setUsername(scanner.nextLine());
            }

            super.print("请输入密码：");
            String password = scanner.nextLine();
            super.print("请再次输入密码：");
            String password1 = scanner.nextLine();
            while (!password.equals(password1)) {
                super.println("俩次密码不一致");
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
                }
                user.setEmail(email);
                verifyEmail=true;
            }

            int telephoneNumber=0;
            super.print("请输入手机号码：");
            telephoneNumber=scanner.nextInt();

            while (!verifyTelephoneNumber) {
                for (int i = 0; i < list.size(); i++) {
                    if(telephoneNumber==(userService.getUserByUserId(i).getTelephoneNumber())){
                        super.print("手机号码重复，请再次请输入手机号码：");
                        telephoneNumber=scanner.nextInt();
                    }
                }
                user.setTelephoneNumber(telephoneNumber);
                verifyTelephoneNumber=true;
            }

            super.print("请输入注册时间：");
            user.setregistrationTime(scanner.nextInt());

            user.setId(userService.getAllUsers().size());

            userService.addUser(user);
        } else
            super.println("你不是管理员无法执行此操作");
    }
}