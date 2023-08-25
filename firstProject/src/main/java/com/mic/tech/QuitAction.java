package com.mic.tech;

import java.util.Scanner;

public class QuitAction extends AbstractAction{
    Scanner scanner=null;
    GlobalState globalState=null;
    QuitAction(Scanner scanner,GlobalState globalState){
        this.scanner=scanner;
        this.globalState=globalState;
    }
    String getActionName(){
        return "QUIT";
    }
    String getDescription(){
        return "你可以退出程序";
    }
    void run(){
        super.println("请输入y/n来决定是否退出程序");
        String answer=scanner.nextLine();
        if(answer.equals("y"))
            globalState.setState();
    }
}
