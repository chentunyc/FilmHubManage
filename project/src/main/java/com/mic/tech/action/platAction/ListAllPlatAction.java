package com.mic.tech.action.platAction;
import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.Film;
import com.mic.tech.kindsOfData.Plat;
import com.mic.tech.kindsOfData.PlatService;

import java.util.*;
import java.text.*;
public class ListAllPlatAction extends AbstractAuthenticatedAction {
    private GlobalState state=null;
    private PlatService platService=null;
    public ListAllPlatAction(GlobalState state, PlatService platService){
        this.state=state;
        this.platService=platService;
    }

    public String getDescription() {
        return "列出所有排片 only manager or receptionist or customers";
    }

    public String getActionName() {
        return "LIST_ALL_PLAT";
    }

    public void perform() {
        Role currentRole=state.getUserRole();
        if(currentRole==Role.MANAGER|currentRole==Role.RECEPTIONIST||currentRole==Role.BRONZE_CUSTOMER||currentRole==Role.SILVER_CUSTOMER||currentRole==Role.GOLD_CUSTOMER){
            if(!platService.getAllPlats().isEmpty()){
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-13s %-13s %-13s %-15s %-15s %-15s %-15s %-3s","screeningHall","time","price","title","director","starring","synopsis","duration");
                System.out.println();
            }
            Date currentDate = new Date(); // 当前日期
            // 计算一周后的日期
            long oneWeekInMillis = 7 * 24 * 60 * 60 * 1000L;
            long oneWeekLater = currentDate.getTime() + oneWeekInMillis;
            Date oneWeekLaterDate = new Date(oneWeekLater);
            for(Plat plat: platService.getAllPlats()){
                String time=plat.getTime();
                Date date=null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                try {
                    date = sdf.parse(time);
                } catch (ParseException e) {
                    System.out.println("日期解析失败: " + e.getMessage());
                }
                if(date.after(currentDate) && date.before(oneWeekLaterDate)) {
                    String screeningHall=plat.getScreeningHall();
                    double price = plat.getPrice();
                    Film film = plat.getFilm();
                    String title = null;
                    String director = null;
                    String starring = null;
                    String synopsis = null;
                    String duration = null;
                    if (film != null) {
                        title = film.getTitle();
                        director = film.getDirector();
                        starring = film.getStarring();
                        synopsis = film.getSynopsis();
                        duration = film.getDuration();
                    }
                    System.out.printf("%-13s %-13s %-13f %-15s %-15s %-15s %-15s %-3s", screeningHall, time, price, title, director, starring, synopsis, duration);
                    System.out.println();
                }
            }
        }
        else {
            super.println("你不是经理无法执行此操作");
        }
    }
}
