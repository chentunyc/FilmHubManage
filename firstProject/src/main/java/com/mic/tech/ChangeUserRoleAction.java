package com.mic.tech;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChangeUserRoleAction extends AbstractAuthenticatedAction{
    UserService userService=null;
    GlobalState state =null;
    Scanner scanner=null;
    ChangeUserRoleAction(UserService userService, GlobalState state, Scanner scanner){
        this.state = state;
        this.userService=userService;
        this.scanner=scanner;
    }

    public void perform(){
        Role currentRole= state.getUserRole();
        if(currentRole==Role.ADMINISTRATOR){
            super.print("请输入需要修改的用户名:");
            String userName=scanner.nextLine();
            User user = userService.getUserByUserName(userName);
            if (user == null) {
                super.println("没有找到该用户");
            } else {
                userService.updateUser(user);
                super.println("已经成功更改用户类型");
                Map<String, Role> roles = new HashMap<>();
                roles.put("1", Role.ADMINISTRATOR);
                roles.put("2", Role.MANAGER);
                roles.put("3", Role.CUSTOMER);
                super.println("可选的角色: ");
                for(Map.Entry<String, Role> entry : roles.entrySet()) {
                    String roleNumber = entry.getKey();
                    String roleName = entry.getValue().toString();
                    super.println(roleNumber + ". " + roleName);
                }
                super.print("请输入你想转换的角色的数字: ");
                String roleNumber = scanner.nextLine();
                if (roles.containsKey(roleNumber)) {
                    user.setRole(roles.get(roleNumber));
                    userService.updateUser(user);
                    super.println("已经成功更改用户类型");
                } else {
                    super.println("数字非法");
                }
            }
        }
        else{
            super.println("你不是管理员");
        }
    }
    public String getDescription(){
        return "你可以更改用户类型";
    }
    public String getActionName(){
        return "CHANGE_USER_ROLE";
    }
}
