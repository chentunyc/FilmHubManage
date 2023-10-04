package com.mic.tech.kindsOfData;

import com.mic.tech.kindsOfData.Film;
import com.mic.tech.kindsOfData.FilmDao;

import java.util.List;

public class FilmService{
    private FilmDao filmDao=null;
    public FilmService(FilmDao filmDao){
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
    public List getFilmByFilmInformation(String title,String director,String starring) {
        return filmDao.getFilmByFilmInformation(title,director,starring);
    }
    public List<Film> getAllFilms() {
        return filmDao.getAllFilms();
    }
}
