package com.mic.tech;

public class ListAllUserAction extends AbstractAuthenticatedAction{
    GlobalState state =null;
    UserService userService=null;
    ListAllUserAction(GlobalState state, UserService userService){
        this.state = state;
        this.userService=userService;
    }
    public void perform(){
        if(state.getUserRole()==Role.ADMINISTRATOR){
            if(!userService.getAllUsers().isEmpty()){
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-3s %-10s %-13s %-10s %-15s %-16s","id","username","role","email","telephoneNumber","registrationTime");
                System.out.println();
            }
            for(User user : userService.getAllUsers()){
                String username = user.getUsername();
                Role role = user.getRole();
                int id= user.getId();
                int telephoneNumber= user.getTelephoneNumber();
                int registrationTime=user.getRegistrationTime();
                String email=user.getEmail();
                System.out.printf("%-3s %-10s %-13s %-10s %-15s %-16s",id,username,role,email,telephoneNumber,registrationTime);
                System.out.println();
            }
        }
        else
            super.println("你不是管理员无法执行此操作");
    }
    public String getDescription(){
        return "列出所有用户信息";
    }
    public String getActionName(){
        return "LIST_ALL_USER";
    }
}
