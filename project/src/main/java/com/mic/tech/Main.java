package com.mic.tech;

import com.mic.tech.kindsOfData.FilmDataDAO;
import com.mic.tech.kindsOfData.PlatDataDAO;
import com.mic.tech.kindsOfData.UserDataDAO;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    private List <AbstractAction> actions= null;
    private Scanner scanner=null;
    private GlobalState globalState=null;
    private UserService userService=null;
    private FilmService filmService=null;
    private PlatService platService=null;
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
    protected void initialize(){
        System.out.println("初始化中...");
        actions=new ArrayList<>();
        scanner=new Scanner(System.in);
        globalState=new GlobalState();
        this.outPut("加载全局变量完成","成功");
        userService=new UserService(new UserDataDAO());
        filmService=new FilmService(new FilmDataDAO());
        platService=new PlatService(new PlatDataDAO());
        this.outPut("服务模块加载完成","成功");
        this.actions.add(new ListAction(actions));
        this.actions.add(new QuitAction(scanner,globalState));
        this.actions.add(new LoginAction(scanner,globalState,userService));
        this.actions.add(new LogoutAction(globalState));
        this.outPut("基本操作加载完成","成功");
        this.actions.add(new ListAllUserAction(globalState,userService));
        this.actions.add(new ListUserAction(globalState,userService,scanner));
        this.actions.add(new ChangeUserRoleAction(userService,globalState,scanner));
        this.actions.add(new ChangeUserPasswordAction(userService,globalState,scanner));
        this.actions.add(new ChangeUserEmailAction(userService,globalState,scanner));
        this.actions.add(new ChangeUserTelephoneNumberAction(userService,globalState,scanner));
        this.actions.add(new DeleteUserAction(globalState,userService,scanner));
        this.outPut("管理操作加载完成","成功");
        this.actions.add(new CustomerRegisterAction(globalState,userService,scanner));
        this.actions.add(new ForgetUserPasswordAction(globalState,userService,scanner));
        this.actions.add(new SoldTicketAction(globalState,platService,userService,scanner));
        this.actions.add(new BuyTicketAction(globalState,platService,userService,scanner));
        this.actions.add(new ListBuyRecordAction(globalState,platService,userService));
        this.actions.add(new PickTicketAction(globalState,platService,userService,scanner));
        this.outPut("顾客操作加载完成","成功");
        this.actions.add(new ListAllFilmAction(globalState,filmService));
        this.actions.add(new ListFilmAction(globalState,filmService,scanner));
        this.actions.add(new ChangeFilmAction(globalState,filmService,scanner));
        this.actions.add(new DeleteFilmAction(globalState,filmService,scanner));
        this.actions.add(new FilmAddAction(globalState,filmService,scanner));
        this.outPut("影片操作加载完成","成功");
        this.actions.add(new PlatAddAction(globalState,filmService,platService,scanner));
        this.actions.add(new ChangePlatAction(globalState,filmService,platService,scanner));
        this.actions.add(new DeletePlatAction(globalState,platService,scanner));
        this.actions.add(new ListAllPlatAction(globalState,platService));
        this.actions.add(new ListPlatAction(globalState,platService,scanner));
        this.outPut("排片操作加载完成","成功");
    }

    protected void run(){
        boolean found=false;
        System.out.println("程序运行中...");
        System.out.println("欢迎您使用本程序");
        System.out.println("请输入LIST来获取可以执行的操作列表");
        System.out.println("有的操作需要先登录才能执行");
        while(this.globalState.isRuning()){
            String username = this.globalState.getUserName();
            username = username != null
                    ? "登录者: " + username.toLowerCase() + "@" + this.globalState.getUserRole().toString()
                    : "";
            System.out.print(username + "> ");
            String actionName = this.scanner.nextLine();
            for(AbstractAction action:actions){
                if(action.getActionName().equals(actionName)){
                    action.run();
                    found=true;
                    break;
                }
            }
            if(!found)
                System.out.println("没有找到可以执行的功能");
        }
        System.out.println("运行结束");
    }
    protected void free(){
        System.out.println("释放资源中...");
        if (this.scanner != null) {
            this.scanner.close();
            this.outPut("关闭扫描器", "OK");
        }
        System.out.println("资源释放完成.");
    }
}
