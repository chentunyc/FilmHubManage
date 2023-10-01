package com.mic.tech;

import java.util.Scanner;

public class FilmAddAction extends AbstractAuthenticatedAction{
    FilmService filmService=null;
    GlobalState state=null;
    Scanner scanner=null;
    FilmAddAction(GlobalState state,FilmService filmService,Scanner scanner){
        this.state=state;
        this.filmService=filmService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "增加影片 only manager";
    }

    public String getActionName() {
        return "FILM_ADD";
    }

    void perform() {
        if(state.getUserRole()==Role.MANAGER){
            super.print("请输入影片标题");
            String title = scanner.nextLine();
            super.print("请输入影片导演");
            String director = scanner.nextLine();
            super.print("请输入影片主演");
            String starring = scanner.nextLine();
            super.print("请输入影片简介");
            String synopsis = scanner.nextLine();
            super.print("请输入影片时长");
            String duration = scanner.nextLine();
            Film film=new Film();
            film.setTitle(title);
            film.setDirector(director);
            film.setStarring(starring);
            film.setSynopsis(synopsis);
            film.setDuration(duration);
            filmService.addFilm(film);
        }
        else {
            super.println("你不是经理");
        }
    }
}
