package com.mic.tech.kindsOfData;

import com.mic.tech.kindsOfData.Film;

public class Plat{
    private String screeningHall=null;
    private double price=0;
    private String time=null;
    private Film film=null;
    private String seat[][]=new String[7][12];
    private String ticketID[][]=new String[7][12];
    private String isPutout[][]=new String[7][12];
    private int totalNumberSeat =84;
    private int availableSeat=84;
    public Plat(){
        for(int i=0;i<7;i++){
            for (int j=0;j<12;j++){
                seat[i][j]="O";
                ticketID[i][j]="null";
                isPutout[i][j]="null";
            }
        }
    }
    public double getPrice() {
        return price;
    }
    public String getScreeningHall() {
        return screeningHall;
    }
    public String getTime() {
        return time;
    }
    public void setPrice(double price) {
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
    public void setTotalNumberSeat(int totalNumberSeat){
        this.totalNumberSeat=totalNumberSeat;
    }
    public void setTicketIDBySeatId(int i, int j, String ticketIDNumber) {
        this.ticketID [i][j]=ticketIDNumber;
    }
    public void setTicketID(String[][] ticketID){this.ticketID=ticketID;}
    public String getTicketIDBySeat(int i, int j) {
        return ticketID[i][j];
    }
    public String [][]getTicketID() {
        return ticketID;
    }
    public String getIsPutoutBySeat(int i, int j){
        return isPutout[i][j];
    }
    public String [][]getIsPutout(){
        return isPutout;
    }
    public void setIsPutoutBySeat(int i, int j, String isPutout) {
        this.isPutout [i][j]=isPutout;
    }
    public void setIsPutout(String isPutout[][]) {
        this.isPutout =isPutout;
    }
}
