package com.mic.tech;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilmService{
    FilmDao filmDao=null;
    FilmService(FilmDao filmDao){
        this.filmDao=filmDao;
    }
    public void addFilm(Film film) {
        filmDao.addFilm(film);
    }

    public void updateFilm(Film film,String title) {
        filmDao.updateFilm(film,title);
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

    public List<Film> getAllFilms() {
        return filmDao.getAllFilms();
    }
}
