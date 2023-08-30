package com.mic.tech;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeleteFilmAction extends AbstractAuthenticatedAction{
    FilmService filmService=null;
    GlobalState state=null;
    Scanner scanner=null;
    DeleteFilmAction(GlobalState state,FilmService filmService,Scanner scanner){
        this.state=state;
        this.filmService=filmService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "删除影片";
    }

    public String getActionName() {
        return "DELETE_FILM";
    }

    void perform() {
        if (state.getUserRole() == Role.MANAGER) {
            super.print("请输入原影片标题");
            String title = scanner.nextLine();
            Film film = filmService.getFilmByFilmTitle(title);
            if (film != null) {
                filmService.deleteFilm(title);
            }
            else {
                super.println("找不到该影片");
            }
        } else {
            super.println("你不是经理");
        }
    }
}
