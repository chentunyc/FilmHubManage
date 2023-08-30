package com.mic.tech;

import java.util.List;

public interface PlatDAO {
    void addPlat(Plat plat);
    void updateFlat(Plat plat);
    void deleteFlat(String time);
    Plat getFlatByFlatTime(String time);
    List<Plat>getAllPlats();
}
