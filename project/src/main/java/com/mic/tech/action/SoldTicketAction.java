package com.mic.tech.action;
import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.action.customerAction.PayAction;
import com.mic.tech.kindsOfData.Plat;
import com.mic.tech.kindsOfData.PlatService;
import com.mic.tech.kindsOfData.User;
import com.mic.tech.kindsOfData.UserService;

import java.util.Scanner;
public class SoldTicketAction extends AbstractAuthenticatedAction {
    private GlobalState state=null;
    private PlatService platService=null;
    private UserService userService=null;
    private Scanner scanner=null;
    public SoldTicketAction(GlobalState state, PlatService platService, UserService userService, Scanner scanner){
        this.state=state;
        this.platService=platService;
        this.userService=userService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "可以售票 only receptionist";
    }
    public String getActionName() {
        return "SOLD_TICKET";
    }
    public void perform() {
        if(state.getUserRole()==Role.RECEPTIONIST) {
            super.println("你可以选择输入用户名或手机号:");
            super.print("请输入USER_NAME或USER_TELEPHONE_NUMBER来选择查询方式:");
            User user = null;
            String selection = scanner.nextLine();
            if (selection.equals("USER_NAME")) {
                super.print("请输入具体的用户名:");
                String userName = scanner.nextLine();
                user = userService.getUserByUserName(userName);
            }
            if (selection.equals("USER_TELEPHONE_NUMBER")) {
                super.print("请输入具体的手机号:");
                String userTelephoneNumber = scanner.nextLine();
                user = userService.getUserByUserTelephoneNumber(userTelephoneNumber);
            }
            Plat plat = null;
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
                        PayAction payAction = new PayAction(seatI, seatJ, plat,user, scanner,platService,userService);
                        payAction.perform();
                        super.println("票ID" + plat.getTicketIDBySeat(seatI, seatJ));
                    } else {
                        plat.setSeatBySeatId(seatI, seatJ, "O");
                    }
                }
            }
        }
        else {
            super.println("你不是前台");
        }
    }
}
