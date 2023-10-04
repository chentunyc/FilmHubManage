package com.mic.tech;

import java.util.Scanner;

public class PickTicketAction extends AbstractAuthenticatedAction{
    PickTicketAction(GlobalState state, PlatService platService, UserService userService, Scanner scanner){
        this.state=state;
        this.platService=platService;
        this.userService=userService;
        this.scanner=scanner;
    }
    GlobalState state=null;
    PlatService platService=null;
    UserService userService=null;
    Scanner scanner=null;
    public String getDescription() {
        return "你可以取票 only customers";
    }
    public String getActionName() {
        return "PICK_TICKET";
    }
    void perform() {
        Role currentRole=state.getUserRole();
        if (currentRole==Role.RECEPTIONIST||currentRole==Role.BRONZE_CUSTOMER||currentRole==Role.SILVER_CUSTOMER) {
            User user=userService.getUserByUserName(state.getUserName());
            user.getBuyRecord();
            super.println("请输入号码");
            String ticketId=scanner.nextLine();
            Plat plat=platService.getPlatByTicketId(ticketId);
            for(int i=0;i<7;i++) {
                for (int j = 0; j < 12; j++) {
                    if(plat.getIsPutoutBySeat(i,j).equals("hasBuied")&&plat.getTicketIDBySeat(i,j).equals(ticketId)){
                        System.out.printf("%-13s %-5s %-15s %-3s", "screeningHall", "time", "title", "duration");
                        System.out.println();
                        String screeningHall=plat.getScreeningHall();
                        String time=plat.getTime();
                        Film film =plat.getFilm();
                        String title =null;
                        String duration =null;
                        if(film!=null) {
                            title = film.getTitle();
                            duration = film.getDuration();
                        }
                        System.out.printf("%-13s %-5s %-15s %-3s", screeningHall, time, title, duration);
                        System.out.println();
                        System.out.println("座位"+"x"+i+"y"+j);
                        plat.setIsPutoutBySeat(i,j,"hasTaken");
                    }
                    else if(plat.getIsPutoutBySeat(i,j).equals("hasTaken")&&plat.getTicketIDBySeat(i,j).equals(ticketId)){
                        super.println("票已经被取走，票只能取一次");
                    }
                }
            }
        }
    }
}
