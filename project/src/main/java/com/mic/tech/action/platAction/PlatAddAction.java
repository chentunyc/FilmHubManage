package com.mic.tech.action.platAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.Film;
import com.mic.tech.kindsOfData.FilmService;
import com.mic.tech.kindsOfData.Plat;
import com.mic.tech.kindsOfData.PlatService;

import java.util.Scanner;

public class PlatAddAction extends AbstractAuthenticatedAction {
    private PlatService platService=null;
    private FilmService filmService=null;
    private GlobalState state=null;
    private Scanner scanner=null;
    public PlatAddAction(GlobalState state,FilmService filmService,PlatService platService,Scanner scanner){
        this.state=state;
        this.filmService=filmService;
        this.platService=platService;
        this.scanner=scanner;
    }

    public String getDescription() {
        return "增加排片 only manager";
    }

    public String getActionName() {
        return "PLAT_ADD";
    }

    public void perform() {
        Film film;
        Plat plat=new Plat();
        if(state.getUserRole()==Role.MANAGER){
            super.print("请输入影片的名字");
            String filmTitle=scanner.nextLine();
            super.print("请输入排片放映厅");
            String screenHall = scanner.nextLine();
            super.print("请输入排片时间段（注意输入格式yyyyMMddHHmm）");
            String time = scanner.nextLine();
            super.print("请输入排片的价格");
            double price = scanner.nextDouble();

            film=filmService.getFilmByFilmTitle(filmTitle);
            plat.setScreeningHall(screenHall);
            plat.setTime(time);
            plat.setFilm(film);
            plat.setPrice(price);
            platService.addPlat(plat);
        }
        else {
            super.println("你不是经理");
        }
    }
}
