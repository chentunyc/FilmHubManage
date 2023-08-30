package com.mic.tech;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilmService implements FilmDao{
    List <Film> list=new ArrayList<>();
    public void addFilm(Film film) {
        list.add(film);
    }

    public void updateFilm(Film film) {
        for(int i=0;i<list.size();i++){
            Film information=list.get(i);
            if (information.getTitle().equals(film.getTitle())||information.getSynopsis().equals(film.getSynopsis())){
                list.get(i).setTitle(film.getTitle());
                list.get(i).setDirector(film.getDirector());
                list.get(i).setStarring(film.getStarring());
                list.get(i).setSynopsis(film.getSynopsis());
                list.get(i).setDuration(film.getDuration());
                break;
            }
        }
    }

    public void deleteFilm(String title) {
        Iterator<Film> iterator = list.iterator();
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getTitle().equals(title)) {
                iterator.remove();
            }
        }
    }

    public Film getFilmByFilmTitle(String title) {
        for (Film film:list){
            if(film.getTitle().equals(title))
                return film;
        }
        return null;
    }

    public Film getFilmByFilmDirector(String director) {
        for (Film film:list){
            if(film.getDirector().equals(director))
                return film;
        }
        return null;
    }

    public Film getFilmByFilmStarring(String starring) {
        for (Film film:list){
            if(film.getStarring().equals(starring))
                return film;
        }
        return null;
    }

    public List<Film> getAllFilms() {
        return list;
    }
}
