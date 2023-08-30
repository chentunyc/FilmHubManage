package com.mic.tech;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.*;

public class ChangeFilmAction extends AbstractAuthenticatedAction{
    FilmService filmService=null;
    GlobalState state=null;
    Scanner scanner=null;
    ChangeFilmAction(GlobalState state, FilmService filmService, Scanner scanner){
        this.state=state;
        this.filmService=filmService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "修改影片信息";
    }

    public String getActionName() {
        return "CHANGE_FILM";
    }

    void perform() {
        if(state.getUserRole()==Role.MANAGER){
            super.print("请输入原影片标题");
            String title=scanner.nextLine();
            Film film=filmService.getFilmByFilmTitle(title);
            if(film!=null) {
                List<String> list = new ArrayList<>();
                list.add("TITLE");
                list.add("DIRECTOR");
                list.add("STARRING");
                list.add("SYNOPSIS");
                list.add("DURATION");
                super.println("可以修改的内容: ");
                for(int i=0;i<list.size();i++){
                    int j=i+1;
                    System.out.println(this.getActionName().toUpperCase() + "> " +j+" "+list.get(i));
                }
                super.print("请输入你想修改影片内容的数字: ");
                String roleNumber = scanner.nextLine();
                super.print("修改的内容: ");
                String temporaryString = scanner.nextLine();
                switch (roleNumber) {
                    case "1" :{
                        film.setTitle(temporaryString);
                        filmService.updateFilm(film);
                        super.println("已经成功更改影片信息");
                        break;
                    }
                    case "2":{
                        film.setDirector(temporaryString);
                        filmService.updateFilm(film);
                        super.println("已经成功更改影片信息");
                        break;
                    }
                    case "3": {
                        film.setStarring(temporaryString);
                        filmService.updateFilm(film);
                        super.println("已经成功更改影片信息");
                        break;
                    }
                    case "4": {
                        film.setSynopsis(temporaryString);
                        filmService.updateFilm(film);
                        super.println("已经成功更改影片信息");
                        break;
                    }
                    case  "5": {
                        film.setDuration(temporaryString);
                        filmService.updateFilm(film);
                        super.println("已经成功更改影片信息");
                        break;
                    }
                    default:{
                        super.println("数字非法");
                    }
                }
            }
            else {
                super.println("找不到该影片");
            }
        }
        else {
            super.println("你不是经理");
        }
    }
}
