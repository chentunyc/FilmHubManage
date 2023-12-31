package com.mic.tech.action.customerAction;
import com.mic.tech.*;
import com.mic.tech.kindsOfData.Plat;
import com.mic.tech.kindsOfData.PlatService;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

import java.util.Scanner;
public class BuyTicketAction extends AbstractAuthenticatedAction {
    private GlobalState state=null;
    private PlatService platService=null;
    private UserService userService=null;
    private Scanner scanner=null;
    public BuyTicketAction(GlobalState state, PlatService platService, UserService userService, Scanner scanner){
        this.state=state;
        this.platService=platService;
        this.userService=userService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "你可以买票 only customers";
    }
    public String getActionName() {
        return "BUY_TICKET";
    }

    public void perform() {
        Role currentRole=state.getUserRole();
        if (currentRole==Role.GOLD_CUSTOMER||currentRole==Role.BRONZE_CUSTOMER||currentRole==Role.SILVER_CUSTOMER) {
            Plat plat = null;
            User user=userService.getUserByUserName(state.getUserName());
            super.print("请输入影片片名:");
            String title = scanner.nextLine();
            super.print("请输入场次时间:");
            String time = scanner.nextLine();
            plat = platService.getFlatBYTimeTitle(time, title);
            if(plat!=null) {
                super.print("请输入行");
                int seatI = scanner.nextInt();
                super.print("请输入列");
                int seatJ = scanner.nextInt();
                seatI--;seatJ--;
                scanner.nextLine();
                if (plat.getSeatBySeatId(seatI, seatJ).equals("O")) {
                    super.println("金额是："+plat.getPrice());
                    super.print("是否执行购买操作y or n");

                    String isBuying = scanner.nextLine();
                    if (isBuying.equals("y")) {
                        PayAction payAction = new PayAction(seatI, seatJ, plat, user, scanner,platService,userService);
                        payAction.perform();
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
                        super.println("票ID: " + plat.getTicketIDBySeat(seatI, seatJ));
                    } else {
                        plat.setSeatBySeatId(seatI, seatJ, "O");
                    }
                }
            }
        }
    }
}
