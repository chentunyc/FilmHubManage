package com.mic.tech;

import java.util.Scanner;

public class ListFilmAction extends AbstractAuthenticatedAction {
    FilmService filmService = null;
    GlobalState state = null;
    Scanner scanner = null;

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
            Film film = null;
            super.println("你可以选择用影片名称、导演、主演查询");
            super.print("请输入TITLE/DIRECTOR/STARRING来选择查询方式:");
            String selection = scanner.nextLine();
            if (selection.equals("TITLE")) {
                super.print("请输入具体的标题:");
                String title = scanner.nextLine();
                film = filmService.getFilmByFilmTitle(title);
            }
            if (selection.equals("DIRECTOR")) {
                super.print("请输入具体的导演:");
                String director = scanner.nextLine();
                film = filmService.getFilmByFilmDirector(director);
            }
            if (selection.equals("STARRING")) {
                super.print("请输入具体的主演:");
                String starring = scanner.nextLine();
                film = filmService.getFilmByFilmStarring(starring);
            }
            if (film != null) {
                String title = film.getTitle();
                String director = film.getDirector();
                String starring = film.getStarring();
                String synopsis = film.getSynopsis();
                String duration = film.getDuration();
                System.out.println(this.getActionName().toUpperCase() + "> ");
                System.out.printf("%-15s %-15s %-15s %-15s %-3s", "title", "director", "starring", "synopsis", "duration");
                System.out.println();
                System.out.printf("%-15s %-15s %-15s %-15s %-3s", title, director, starring, synopsis, duration);
                System.out.println();
            } else {
                super.println("你不是经理");
            }
        }
    }
}