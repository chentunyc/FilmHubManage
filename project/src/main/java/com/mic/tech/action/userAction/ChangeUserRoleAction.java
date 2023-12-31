package com.mic.tech.action.userAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChangeUserRoleAction extends AbstractAuthenticatedAction {
    private UserService userService=null;
    private GlobalState state =null;
    private Scanner scanner=null;
    public ChangeUserRoleAction(UserService userService, GlobalState state, Scanner scanner){
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
            } else if(userName.equals("admin")){
                super.println("预设管理员账号不允许修改");
            }else {
                Map<String, Role> roles = new HashMap<>();
                roles.put("1", Role.MANAGER);
                roles.put("2", Role.RECEPTIONIST);
                roles.put("3", Role.BRONZE_CUSTOMER);
                roles.put("4",Role.SILVER_CUSTOMER);
                roles.put("5",Role.GOLD_CUSTOMER);
                super.println("可选的角色: ");
                for(Map.Entry<String, Role> entry : roles.entrySet()) {
                    String roleNumber = entry.getKey();
                    String roleName = entry.getValue().toString();
                    super.println(roleNumber + ". " + roleName);
                }
                super.print("请输入你想转换的角色的数字: ");
                String roleNumber = scanner.nextLine();
                if (roles.containsKey(roleNumber)) {
                    user.setRole((AbstractAuthenticatedAction.Role)roles.get(roleNumber));
                    this.userService.updateUser(user);
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
