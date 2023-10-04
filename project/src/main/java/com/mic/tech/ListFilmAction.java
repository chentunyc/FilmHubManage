package com.mic.tech;

import java.util.List;
import java.util.Scanner;

public class ListFilmAction extends AbstractAuthenticatedAction {
    private FilmService filmService = null;
    private GlobalState state = null;
    private Scanner scanner = null;

    ListFilmAction(GlobalState state, FilmService filmService, Scanner scanner) {
        this.state = state;
        this.filmService = filmService;
        this.scanner = scanner;
    }

    public String getDescription() {
        return "查询影片 only manager";
    }

    public String getActionName() {
        return "LIST_FILM";
    }

    void perform() {
        if (state.getUserRole() == Role.MANAGER) {
            List<Film>list=null;
            super.println("你可以选择根据影片名称、导演、主演单独或者组合查询");
            super.print("请输入TITLE/DIRECTOR/STARRING:");
            String title = scanner.nextLine();
            String director=scanner.nextLine();
            String starring=scanner.nextLine();
            list=filmService.getFilmByFilmInformation(title,director,starring);
            if (list != null) {
                for(Film film:list) {
                    String titleN = film.getTitle();
                    String directorN = film.getDirector();
                    String starringN = film.getStarring();
                    String synopsis = film.getSynopsis();
                    String duration = film.getDuration();
                    System.out.println(this.getActionName().toUpperCase() + "> ");
                    System.out.printf("%-15s %-15s %-15s %-15s %-3s", "title", "director", "starring", "synopsis", "duration");
                    System.out.println();
                    System.out.printf("%-15s %-15s %-15s %-15s %-3s", titleN, directorN, starringN, synopsis, duration);
                    System.out.println();
                }
            } else {
                super.println("你不是经理");
            }
        }
    }
}