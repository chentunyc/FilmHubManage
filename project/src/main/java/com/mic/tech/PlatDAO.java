package com.mic.tech;

import java.util.List;

public interface PlatDAO {
    void addPlat(Plat plat);
    void updatePlat(Plat plat);
    void updatePlatByData(Plat plat,String screeningHall,int price,String time,Film film,String seat[][]);
    void deletePlat(String time);
    Plat getPlatBYTimeTitle(String time, String title);
    public Plat getPlatByTicketId(String ticketId);
    List<Plat>getAllPlats();
}
