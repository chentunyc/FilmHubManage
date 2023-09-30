package com.mic.tech;

import java.util.Scanner;

public class DeletePlatAction extends AbstractAuthenticatedAction{
    PlatService platService=null;
    GlobalState state=null;
    Scanner scanner=null;
    DeletePlatAction(GlobalState state,PlatService platService,Scanner scanner){
        this.state=state;
        this.platService=platService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "删除排片 only manager";
    }

    public String getActionName() {
        return "DELETE_PLAT";
    }

    void perform() {
        if (state.getUserRole() == AbstractAuthenticatedAction.Role.MANAGER) {
            super.print("请输入原排片时间");
            String time = scanner.nextLine();
            super.print("请输入原影片片名:");
            String title=scanner.nextLine();
            Plat plat= platService.getFlatBYTimeTitle(time,title);
            if (plat != null) {
                platService.deleteFlat(time);
            }
            else {
                super.println("找不到该排片");
            }
        } else {
            super.println("你不是经理");
        }
    }
}
