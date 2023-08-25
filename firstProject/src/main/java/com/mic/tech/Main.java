package com.mic.tech;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    private List <AbstractAction> actions= null;
    private Scanner scanner=null;
    private GlobalState globalState=null;
    public static void main(String[]args){
        Main app=new Main();
        app.initialize();
        try {
            app.run();
        }
        finally {
            app.free();
        }
    }
    private void outPut(String string,String description){
        int totalLength=100;
        int strLength=string.length();
        int desLength=description.length();
        int dotsLength=totalLength-strLength-desLength-4;
        if (strLength > 90 || desLength > 90) {
            throw new IllegalArgumentException("str or des length exceeds 70 characters");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" ");

        for (int i = 0; i < dotsLength; i++) {
            sb.append("·");
        }

        sb.append(" [");
        sb.append(description);
        sb.append("]");

        String result = sb.toString();
        System.out.println(result);
    }
    public void initialize(){
        System.out.println("初始化中");
        actions=new ArrayList<>();
        scanner=new Scanner(System.in);
        globalState=new GlobalState();
        this.outPut("成功加载全局变量","OK");
        this.actions.add(new ListAction(actions));
        this.actions.add(new QuitAction(scanner,globalState));
        this.actions.add(new LoginAction(scanner));
        this.outPut("操作加载完成","OK");
    }

    public void run(){
        boolean found=false;
        System.out.println("程序运行中...");
        System.out.println("欢迎您使用本程序");
        System.out.println("请输入LIST来获取可以执行的操作");
        while(globalState.isRuning){
            System.out.print(">");
            String antionName=scanner.nextLine();
            for(AbstractAction action:actions){
                if(action.getActionName().equals(antionName)){
                    action.run();
                    found=true;
                    break;
                }
            }
            if(!found)
                System.out.println("没有找到可以执行的功能");
        }
    }
    public void free(){
        System.out.println("释放资源中...");
        if (this.scanner != null) {
            this.scanner.close();
            this.outPut("关闭扫描器", "OK");
        }
        System.out.println("资源释放完成.");
    }
}
