package com.mic.tech;

import java.util.List;

public interface FilmDao {
    void addFilm(Film film);
    void updateFilm(Film film,String title);
    void deleteFilm(String title);
    Film getFilmByFilmTitle(String title);
    List getFilmByFilmInformation(String title,String director,String starring);
    List<Film> getAllFilms();
}
