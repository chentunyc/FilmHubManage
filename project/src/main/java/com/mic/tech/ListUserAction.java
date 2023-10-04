package com.mic.tech;

import java.util.Scanner;

public class ListUserAction extends AbstractAuthenticatedAction{
    GlobalState state =null;
    UserService userService=null;
    Scanner scanner=null;
    ListUserAction(GlobalState state, UserService userService, Scanner scanner){
        this.state = state;
        this.userService=userService;
        this.scanner=scanner;
    }
    public void perform(){
        if(state.getUserRole()==Role.ADMINISTRATOR){
            User user=null;
            super.println("你可以选择用id/姓名查询");
            super.print("请输入ID或USER_NAME来选择查询方式:");
            String selection=scanner.nextLine();
            if(selection.equals("ID")) {
                super.print("请输入具体的ID:");
                int id=scanner.nextInt();
                user = userService.getUserByUserId(id);
            }
            if(selection.equals("USER_NAME")) {
                super.print("请输入具体的用户名:");
                String userName=scanner.nextLine();
                user = userService.getUserByUserName(userName);
            }
            if(user!=null) {
                Role role = user.getRole();
                String telephoneNumber = user.getTelephoneNumber();
                int registrationTime = user.getRegistrationTime();
                String email = user.getEmail();
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-20s %-20s %-15s %-16s","role","email","telephoneNumber","registrationTime");
                System.out.println();
                System.out.printf("%-20s %-20s %-15s %-16d",role,email,telephoneNumber,registrationTime);
                System.out.println();
            }
        }
        else if(state.getUserRole()== Role.MANAGER){
            User user=null;
            super.println("你可以选择用id/姓名查询");
            super.print("请输入ID或USER_NAME来选择查询方式:");
            String selection=scanner.nextLine();
            if(selection.equals("ID")) {
                super.print("请输入具体的ID:");
                int id=scanner.nextInt();
                user = userService.getUserByUserId(id);
            }
            if(selection.equals("USER_NAME")) {
                super.print("请输入具体的用户名:");
                String userName=scanner.nextLine();
                user = userService.getUserByUserName(userName);
            }
            if(user!=null) {
                Role role = user.getRole();
                if(role==Role.BRONZE_CUSTOMER||role==Role.SILVER_CUSTOMER||role==Role.GOLD_CUSTOMER) {
                    String roleName = role.name();
                    String telephoneNumber = user.getTelephoneNumber();
                    int registrationTime = user.getRegistrationTime();
                    double purchaseAmount = user.getPurchaseAmount();
                    int purchaseNumber = user.getPurchaseNumber();
                    String email = user.getEmail();
                    System.out.println(this.getActionName().toUpperCase() + "> ");
                    System.out.printf("%-20s %-15s %-15s %-20s %-15s %-16s", "role", "purchaseAmount", "purchaseNumber","email", "telephoneNumber", "registrationTime");
                    System.out.println();
                    System.out.printf("%-20s %-15d %-15d %-20s %-15s %-16d", roleName, purchaseAmount, purchaseNumber,email, telephoneNumber, registrationTime);
                    System.out.println();
                }
            }
        }
        else
            super.println("你不是管理员或者经理或者客户无法执行此操作");
    }
    public String getDescription(){
        return "列出指定用户信息 only admin or manager or customers";
    }
    public String getActionName(){
        return "LIST_USER";
    }
}
