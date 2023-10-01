package com.mic.tech;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilmService implements FilmDao{
    FilmDao filmDao=null;
    FilmService(FilmDao filmDao){
        this.filmDao=filmDao;
    }
    public void addFilm(Film film) {
        filmDao.addFilm(film);
    }

    public void updateFilm(Film film) {
        filmDao.updateFilm(film);
    }

    public void deleteFilm(String title) {
        filmDao.deleteFilm(title);
    }

    public Film getFilmByFilmTitle(String title) {
        return filmDao.getFilmByFilmTitle(title);
    }

    public Film getFilmByFilmDirector(String director) {
        return filmDao.getFilmByFilmDirector(director);
    }

    public Film getFilmByFilmStarring(String starring) {
        return filmDao.getFilmByFilmStarring(starring);
    }
    public void updateFilmByData(Film film,String title,String director,String starring,String synopsis,String duration){
        filmDao.updateFilmByData(film,title,director,starring,synopsis,duration);
    }
    public List<Film> getAllFilms() {
        return filmDao.getAllFilms();
    }
}
