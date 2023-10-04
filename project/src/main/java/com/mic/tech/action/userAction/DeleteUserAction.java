package com.mic.tech.action.userAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

import java.util.Scanner;

public class DeleteUserAction extends AbstractAuthenticatedAction {
    private UserService userService=null;
    private GlobalState state=null;
    private  Scanner scanner=null;
    public DeleteUserAction(GlobalState state,UserService userService,Scanner scanner){
        this.state=state;
        this.userService=userService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "删除用户 only admin";
    }

    public String getActionName() {
        return "DELETE_USER";
    }

    public void perform() {
        AbstractAuthenticatedAction.Role currentRole= state.getUserRole();
        if(currentRole==Role.ADMINISTRATOR){
            super.print("请输入需要修改的用户名:");
            String userName=scanner.nextLine();
            User user = userService.getUserByUserName(userName);
            if (user == null) {
                super.println("没有找到该用户");
            } else {
                super.print("你确定删除" + userName + "吗? (y/n) ");
                String confirm = scanner.nextLine();
                if (confirm.equals("y")) {
                    userService.deleteUser(userName);
                    super.println("成功执行删除操作");
                } else {
                    super.println("取消执行删除操作");
                }
            }
        }
        else
            super.println("你不是管理员");
    }
}
