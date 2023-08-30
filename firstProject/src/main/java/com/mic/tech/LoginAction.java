package com.mic.tech;
import java.util.Scanner;
import com.mic.tech.AbstractAuthenticatedAction.Role;
public class LoginAction extends AbstractAction{
    private Scanner scanner=null;
    private GlobalState state=null;
    private UserService userService=null;

    LoginAction(Scanner scanner,GlobalState state,UserService userService){
        this.scanner=scanner;
        this.state=state;
        this.userService=userService;
    }
    public String getActionName(){
        return "LOGIN";
    }
    public String getDescription(){
        return "你可以登录你的账号";
    }
    public void run() {
        System.out.print("请输入用户名:");
        String userName=scanner.nextLine();
        System.out.print("请输入密码:");
        String password=scanner.nextLine();
        Role role=login(userName,password);
        if (role!=null){
            state.setUserName(userName);
            state.setRole(role);
            state.setIsAuthenticated(true);
            System.out.println("登录成功");
        }
        else{
            System.out.println("登录失败");
        }
    }
    private Role login(String userName,String password){
        User user = this.userService.getUserByUserName(userName);
        if(userName.equals(state.getAdministratorName())&&password.equals(state.getAdministratorPassword())) {
            return Role.ADMINISTRATOR;
        }
        else if (user != null && user.getPassword().equals(password)) {
            return user.getRole();
        }
        else{
            return null;
        }
    }
}
