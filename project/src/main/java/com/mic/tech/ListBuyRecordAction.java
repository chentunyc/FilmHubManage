package com.mic.tech;

import java.util.List;

public class ListBuyRecordAction extends AbstractAuthenticatedAction {
    private GlobalState state = null;
    private PlatService platService = null;
    private UserService userService = null;
    ListBuyRecordAction(GlobalState state, PlatService platService, UserService userService){
        this.state=state;
        this.platService=platService;
        this.userService=userService;
    }
    public String getDescription() {
        return "你可以查看购买记录 only customers";
    }

    public String getActionName() {
        return "LIST_BUY_RECORD";
    }

    void perform() {
        Role currentRole = state.getUserRole();
        if (currentRole == Role.BRONZE_CUSTOMER || currentRole == Role.SILVER_CUSTOMER||currentRole==Role.GOLD_CUSTOMER) {
            User user = userService.getUserByUserName(state.getUserName());
            List list0 = user.getBuyRecord();
            List list1 = user.getBuyTimeRecord();
            for (int x = 0; x < list0.size(); x++) {
                String ticketId = list0.get(x).toString();
                Plat plat = platService.getPlatByTicketId(ticketId);
                if(plat!=null) {
                    System.out.printf("%-13s %-5s %-15s %-3s", "screeningHall", "time", "title", "duration");
                    System.out.println();
                    String screeningHall = plat.getScreeningHall();
                    String time = plat.getTime();
                    Film film = plat.getFilm();
                    String title = null;
                    String duration = null;
                    if (film != null) {
                        title = film.getTitle();
                        duration = film.getDuration();
                    }
                    System.out.printf("%-13s %-5s %-15s %-3s", screeningHall, time, title, duration);
                    System.out.println();
                    System.out.println("id:"+list0.get(x).toString()+" "+"time:"+ list1.get(x).toString());
                }
            }
        }
    }
}