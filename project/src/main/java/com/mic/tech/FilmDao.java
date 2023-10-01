package com.mic.tech;

import java.util.List;

public interface FilmDao {
    void addFilm(Film film);
    void updateFilm(Film film);
    void updateFilmByData(Film film,String title,String director,String starring,String synopsis,String duration);
    void deleteFilm(String title);
    Film getFilmByFilmTitle(String title);
    Film getFilmByFilmDirector(String director);
    Film getFilmByFilmStarring(String starring);
    List<Film> getAllFilms();
}
