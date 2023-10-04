package com.mic.tech;

import java.util.List;

public interface PlatDAO {
    void addPlat(Plat plat);
    void updatePlat(Plat plat,String time);
    void deletePlat(String time);
    Plat getPlatByTimeTitle(String time, String title);
    public Plat getPlatByTicketId(String ticketId);
    List<Plat>getAllPlats();
}
