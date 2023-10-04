package com.mic.tech.kindsOfData;

import com.mic.tech.kindsOfData.Film;

import java.util.List;

public interface FilmDao {
    public void addFilm(Film film);
    public void updateFilm(Film film,String title);
    public void deleteFilm(String title);
    public Film getFilmByFilmTitle(String title);
    public List getFilmByFilmInformation(String title,String director,String starring);
    public List<Film> getAllFilms();
}
