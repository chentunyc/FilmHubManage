package com.mic.tech.action.filmAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.Film;
import com.mic.tech.kindsOfData.FilmService;

import java.util.Scanner;

public class DeleteFilmAction extends AbstractAuthenticatedAction {
    private FilmService filmService=null;
    private GlobalState state=null;
    private Scanner scanner=null;
    public DeleteFilmAction(GlobalState state,FilmService filmService,Scanner scanner){
        this.state=state;
        this.filmService=filmService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "删除影片 only manager";
    }

    public String getActionName() {
        return "DELETE_FILM";
    }

    public void perform() {
        if (state.getUserRole() == Role.MANAGER) {
            super.print("请输入原影片标题");
            String title = scanner.nextLine();
            Film film = filmService.getFilmByFilmTitle(title);
            if (film != null) {
                super.print("删除后无法恢复，请用户确认是否继续删除操作。y/n");
                String s=scanner.nextLine();
                if(s.equals("y"))
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
