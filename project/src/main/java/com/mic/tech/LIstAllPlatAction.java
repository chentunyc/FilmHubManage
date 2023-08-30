package com.mic.tech;

public class LIstAllPlatAction extends AbstractAuthenticatedAction{
    GlobalState state=null;
    PlatService platService=null;
    LIstAllPlatAction(GlobalState state,PlatService platService){
        this.state=state;
        this.platService=platService;
    }

    public String getDescription() {
        return "列出所有排片";
    }

    public String getActionName() {
        return "LIST_ALL_PLAT";
    }

    void perform() {
        if(state.getUserRole()==Role.MANAGER||state.getUserRole()==Role.RECEPTIONIST){
            if(!platService.getAllPlats().isEmpty()){
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-13s %-5s %-5s %-15s %-15s %-15s %-15s %-3s","screeningHall","time","price","title","director","starring","synopsis","duration");
                System.out.println();
            }
            for(Plat plat: platService.getAllPlats()){
                String screeningHall=plat.getScreeningHall();
                String time=plat.getTime();
                int price=plat.getPrice();
                Film film =plat.getFilm();
                String title =null;
                String director =null;
                String starring =null;
                String synopsis =null;
                String duration =null;
                if(film!=null) {
                    title = film.getTitle();
                    director = film.getDirector();
                    starring = film.getStarring();
                    synopsis = film.getSynopsis();
                    duration = film.getDuration();
                }
                System.out.printf("%-13s %-5s %-5d %-15s %-15s %-15s %-15s %-3s", screeningHall, time, price, title, director, starring, synopsis, duration);
                System.out.println();
            }
        }
        else {
            super.println("你不是经理无法执行此操作");
        }
    }
}
