package com.mic.tech.action.platAction;

import com.mic.tech.AbstractAuthenticatedAction;
import com.mic.tech.GlobalState;
import com.mic.tech.kindsOfData.Film;
import com.mic.tech.kindsOfData.FilmService;
import com.mic.tech.kindsOfData.Plat;
import com.mic.tech.kindsOfData.PlatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ChangePlatAction extends AbstractAuthenticatedAction {
    private PlatService platService = null;
    private FilmService filmService = null;
    private GlobalState state = null;
    private Scanner scanner = null;

    public ChangePlatAction(GlobalState state, FilmService filmService, PlatService platService, Scanner scanner) {
        this.state = state;
        this.filmService = filmService;
        this.platService = platService;
        this.scanner = scanner;
    }

    public String getDescription() {
        return "改变排片信息 only manager";
    }

    public String getActionName() {
        return "CHANGE_PLAT";
    }

    public void perform() {
        if (state.getUserRole() == Role.MANAGER) {
            super.print("请输入原排片时间");
            String time = scanner.nextLine();
            super.print("请输入原影片片名:");
            String title=scanner.nextLine();
            Plat plat = platService.getFlatBYTimeTitle(time,title);
            if (plat != null) {
                List<String> list = new ArrayList<>();
                list.add("SCREENINGHALL");
                list.add("PRICE");
                list.add("TIME");
                list.add("FILM");
                list.add("NULL");
                super.println("可以修改的内容: ");
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    System.out.println(this.getActionName().toUpperCase() + "> " + j + " " + list.get(i));
                }
                super.print("请输入你想修改影片内容的数字: ");
                String roleNumber = scanner.nextLine();

                switch (roleNumber) {
                    case "1": {
                        super.print("修改的内容: ");
                        String temporaryString = scanner.nextLine();
                        plat.setScreeningHall(temporaryString);
                        platService.updatePlat(plat,time);
                        super.println("已经成功更改排片放映厅");
                        break;
                    }
                    case "2": {
                        super.print("修改的内容: ");
                        double temporaryString = scanner.nextDouble();
                        plat.setPrice(temporaryString);
                        platService.updatePlat(plat,time);
                        super.println("已经成功更改排片价格");
                        break;
                    }
                    case "3": {
                        super.print("修改的内容: ");
                        String temporaryString = scanner.nextLine();
                        plat.setTime(temporaryString);
                        platService.updatePlat(plat,time);
                        super.println("已经成功更改排片时间");
                        break;
                    }
                    case "4": {
                        super.print("修改后影片标题: ");
                        String temporaryString = scanner.nextLine();
                        Film film;
                        film = filmService.getFilmByFilmTitle(temporaryString);
                        plat.setFilm(film);
                        platService.updatePlat(plat,time);
                        super.println("已经成功更改排片影片");
                        break;
                    }
                    case "5": {
                        plat.setFilm(null);
                        plat.setTime(null);
                        plat.setScreeningHall(null);
                        plat.setPrice(0);
                        platService.updatePlat(plat,time);
                        super.println("已经成功排空");
                        break;
                    }
                    default: {
                        super.println("数字非法");
                    }
                }
            } else {
                super.println("找不到该排片");
            }
        } else {
            super.println("你不是经理");
        }
    }
}