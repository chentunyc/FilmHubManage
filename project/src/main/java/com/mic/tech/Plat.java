package com.mic.tech;
public class Plat{
    private String screeningHall=null;
    private int price=0;
    private String time=null;
    private Film film=null;
    private String seat[][]=new String[7][12];
    private String ticketID[][]=new String[7][12];
    private String isPutout[][]=new String[7][12];
    private int totalNumberSeat =84;
    private int availableSeat=84;
    Plat(){
        for(int i=0;i<7;i++){
            for (int j=0;j<12;j++){
                seat[i][j]="O";
                ticketID[i][j]="null";
                isPutout[i][j]="null";
            }
        }
    }
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
    public String[][]getSeat(){
        return seat;
    }
    public String getSeatBySeatId(int i,int j){
        return seat[i][j];
    }
    public void setSeatBySeatId(int i,int j,String state){
        seat[i][j]=state;
    }
    public void setSeat(String[][] seat) {
        this.seat = seat;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public int getTotalNumberSeat() {
        return totalNumberSeat;
    }

    public void setTicketID(int i,int j,String ticketIDNumber) {
        this.ticketID [i][j]=ticketIDNumber;
    }
    public String getTicketID(int i,int j) {
        return ticketID[i][j];
    }
    public String getIsPutout(int i,int j){
        return isPutout[i][j];
    }
    public void setIsPutout(int i,int j,String isPutout) {
        this.isPutout [i][j]=isPutout;
    }
}
