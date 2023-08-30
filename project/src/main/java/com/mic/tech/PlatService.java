package com.mic.tech;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlatService implements PlatDAO{
    List<Plat> list=new ArrayList<>();
    public void addPlat(Plat plat) {
        list.add(plat);
    }

    public void updateFlat(Plat plat) {
        for(int i=0;i<list.size();i++){
            Plat information=list.get(i);
            if (information.getTime().equals(plat.getTime())){
                list.get(i).setFilm(plat.getFilm());
                list.get(i).setPrice(plat.getPrice());
                list.get(i).setScreeningHall(plat.getScreeningHall());
                list.get(i).setTime(plat.getTime());
                break;
            }
        }
    }

    public void deleteFlat(String time) {
        Iterator<Plat> iterator = list.iterator();
        while (iterator.hasNext()) {
            Plat plat = iterator.next();
            if (plat.getTime().equals(time)) {
                iterator.remove();
            }
        }
    }

    public Plat getFlatByFlatTime(String time) {
        for (Plat plat:list){
            if(plat.getTime().equals(time))
                return plat;
        }
        return null;
    }

    public List<Plat> getAllPlats() {
        return list;
    }
}
