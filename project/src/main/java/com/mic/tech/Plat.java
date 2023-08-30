package com.mic.tech;

public class Plat {
    private String screeningHall;
    private int price;
    private String time;
    private Film film;

    public int getPrice() {
        return price;
    }

    public String getScreeningHall() {
        return screeningHall;
    }

    public String getTime() {
        return time;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setScreeningHall(String screeningHall) {
        this.screeningHall = screeningHall;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
