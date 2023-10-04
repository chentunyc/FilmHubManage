package com.mic.tech.kindsOfData;

import com.mic.tech.kindsOfData.Plat;

import java.util.List;

public interface PlatDAO {
    public void addPlat(Plat plat);
    public void updatePlat(Plat plat,String time);
    public void deletePlat(String time);
    public Plat getPlatByTimeTitle(String time, String title);
    public Plat getPlatByTicketId(String ticketId);
    public List<Plat>getAllPlats();
}
