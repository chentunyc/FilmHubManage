package com.mic.tech;

public class ListAllUserAction extends AbstractAuthenticatedAction{
    private GlobalState state =null;
    private UserService userService=null;
    ListAllUserAction(GlobalState state, UserService userService){
        this.state = state;
        this.userService=userService;
    }
    public void perform(){
        if(state.getUserRole()==Role.ADMINISTRATOR){
            if(!userService.getAllUsers().isEmpty()){
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-3s %-20s %-20s %-20s %-15s %-16s","id","username","role","email","telephoneNumber","registrationTime");
                System.out.println();
            }
            for(User user : userService.getAllUsers()){
                String username = user.getUsername();
                Role role = user.getRole();
                int id= user.getId();
                String telephoneNumber= user.getTelephoneNumber();
                int registrationTime=user.getRegistrationTime();
                String email=user.getEmail();
                System.out.printf("%-3s %-20s %-20s %-20s %-15s %-16s",id,username,role,email,telephoneNumber,registrationTime);
                System.out.println();
            }
        }
        else if(state.getUserRole()==Role.MANAGER){
            if(!userService.getAllUsers().isEmpty()){
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-20s %-15s %-15s %-3s %-10s %-10s %-15s %-16s","customer","purchaseAmount","purchaseNumber","id","username","email","telephoneNumber","registrationTime");
                System.out.println();
            }
            for(User user : userService.getAllUsers()){
                String username = user.getUsername();
                Role role = user.getRole();
                if(role==Role.BRONZE_CUSTOMER||role==Role.SILVER_CUSTOMER||role==Role.GOLD_CUSTOMER) {
                    String roleName=role.name();
                    int id = user.getId();
                    String telephoneNumber = user.getTelephoneNumber();
                    int registrationTime = user.getRegistrationTime();
                    double purchaseAmount = user.getPurchaseAmount();
                    int purchaseNumber = user.getPurchaseNumber();
                    String email = user.getEmail();
                    System.out.printf("%-20s %-15d %-15d %-3s %-10s %-10s %-15s %-16s",roleName, purchaseAmount, purchaseNumber, id, username, email, telephoneNumber, registrationTime);
                    System.out.println();
                }
            }
        }
        else {
            super.println("你不是管理员或者经理无法执行此操作");
        }

    }
    public String getDescription(){
        return "列出所有用户信息 only admin or manager";
    }
    public String getActionName(){
        return "LIST_ALL_USER";
    }
}
