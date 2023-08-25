package com.mic.tech;

import java.util.Scanner;

public class LoginAction extends AbstractAction{
    Scanner scanner=null;
    LoginAction(Scanner scanner){
        this.scanner=scanner;
    }
    String getActionName(){
        return "LOGIN";
    }
    String getDescription(){
        return "你可以登录你的账号";
    }
    void run() {

    }
}
