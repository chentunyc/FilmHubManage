package com.mic.tech;

import java.util.List;

public class PlatService{
    PlatDAO platDao =null;
    PlatService(PlatDAO platDao){
        this.platDao = platDao;
    }
    public void addPlat(Plat plat) {
        platDao.addPlat(plat);
    }
    public void updatePlat(Plat plat,String time) {
        platDao.updatePlat(plat,time);
    }
    public void deleteFlat(String time) {
        platDao.deletePlat(time);
    }

    public Plat getFlatBYTimeTitle(String time, String title) {
        return platDao.getPlatByTimeTitle(time,title);
    }
    public Plat getPlatByTicketId(String ticketId){
        return platDao.getPlatByTicketId(ticketId);
    }
    public List<Plat> getAllPlats() {
        return platDao.getAllPlats();
    }
}
