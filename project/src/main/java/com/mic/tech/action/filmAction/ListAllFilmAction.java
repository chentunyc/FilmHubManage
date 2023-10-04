package com.mic.tech.action.filmAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.Film;
import com.mic.tech.kindsOfData.FilmService;

public class ListAllFilmAction extends AbstractAuthenticatedAction {
    private GlobalState state=null;
    private FilmService filmService=null;
    public ListAllFilmAction(GlobalState state,FilmService filmService){
        this.state=state;
        this.filmService=filmService;
    }
    public String getDescription() {
        return "列出所有影片信息 only manager or receptionist";
    }

    public String getActionName() {
        return "LIST_ALL_FILM";
    }

    public void perform() {
        if(state.getUserRole()==Role.MANAGER||state.getUserRole()==Role.RECEPTIONIST){
            if(!filmService.getAllFilms().isEmpty()){
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-15s %-15s %-15s %-15s %-3s","title","director","starring","synopsis","duration");
                System.out.println();
            }
            for(Film film: filmService.getAllFilms()){
                String title=film.getTitle();
                String director=film.getDirector();
                String starring=film.getStarring();
                String synopsis=film.getSynopsis();
                String duration=film.getDuration();
                System.out.printf("%-15s %-15s %-15s %-15s %-3s",title,director,starring,synopsis,duration);
                System.out.println();
            }
        }
        else {
            super.println("你不是经理无法执行此操作");
        }
    }
}
