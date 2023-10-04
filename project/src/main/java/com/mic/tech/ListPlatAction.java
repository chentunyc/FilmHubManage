package com.mic.tech;

import java.util.Scanner;

public class ListPlatAction extends AbstractAuthenticatedAction{
    private GlobalState state=null;
    private PlatService platService=null;
    private  Scanner scanner=null;
    ListPlatAction(GlobalState state,PlatService platService,Scanner scanner){
        this.state=state;
        this.platService=platService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "获得场次中的座位 only customers or receptionist";
    }

    public String getActionName() {
        return "LIST_PLAT";
    }

    void perform() {
        Role currentRole=state.getUserRole();
        if(currentRole==Role.RECEPTIONIST||currentRole==Role.BRONZE_CUSTOMER||currentRole==Role.SILVER_CUSTOMER||currentRole==Role.GOLD_CUSTOMER){
            Plat plat=null;
            super.print("请输入影片片名:");
            String title=scanner.nextLine();
            super.print("请输入场次时间:");
            String time=scanner.nextLine();
            plat=platService.getFlatBYTimeTitle(time,title);
            if(plat!=null) {
                super.println("总座位数"+plat.getTotalNumberSeat());
                super.println("剩余座位"+plat.getAvailableSeat());
                System.out.printf("%2s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s\n"," ","1","2","3","4","5","6","7","8","9","10","11","12");
                for(int i=0;i<7;i++){
                    System.out.printf("%s%s",i+1," ");
                    for (int j=0;j<12;j++){
                        String show=plat.getSeatBySeatId(i,j);
                        if(j==9)
                            System.out.print(" ");
                        System.out.printf("%-3s",show);
                    }
                    System.out.println();
                }
            }
        }
        else {
            super.println("你不是用户或者接待员");
        }
    }
}
