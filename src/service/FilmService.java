package service;

import exception.*;
import model.Film;
import repository.FilmRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public Film createFilm(Film film) throws SQLException {
        film.validate();
        if(filmRepository.existsByName(film.getName())){
            throw new DuplicateResourceException("Film with the name:" + film.getName() + " already exists");
        }
        return filmRepository.create(film);
    }

    public ArrayList<Film> getAllFilms(){
        return filmRepository.getAll();
    }

    public Film getFilmById(int id){
        return filmRepository.getById(id);
    }

    public Film updateFilm(int id, Film film) throws SQLException {
        film.validate();
        return filmRepository.update(id, film);
    }

    public void deleteFilm(int id){
        filmRepository.delete(id);
    }
}
