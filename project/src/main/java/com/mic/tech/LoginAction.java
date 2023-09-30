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
        return "你可以登录你的账号 everyone";
    }
    public void run() {
        int tryNumber=0;
        String userName=null;
        String password=null;
        boolean locked=false;
        Role role=null;
        while (!locked&&role==null){
            System.out.print("请输入用户名:");
            userName = scanner.nextLine();
            System.out.print("请输入密码:");
            password = scanner.nextLine();
            role = login(userName, password);
            tryNumber++;
            if(tryNumber==5){
                locked=true;
                break;
            }
        }
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
        if (user != null && user.getPassword().equals(password)) {
            return user.getRole();
        }
        else{
            return null;
        }
    }
}
