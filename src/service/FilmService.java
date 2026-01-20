package service;

import exception.*;
import model.Film;
import repository.FilmRepository;

import java.util.ArrayList;

public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public Film create(Film film){
        film.validate();
        if(filmRepository.existsByName(film.getName())){
            throw new DuplicateResourceException("Film with the name:" + " already exists");
        }
        return filmRepository.create(film);
    }

    public ArrayList<Film> getAll(){
        return filmRepository.getAll();
    }

    public Film getById(int id){
        return filmRepository.getById(id);
    }

    public Film update(int id, Film film){
        film.validate();
        if(filmRepository.existsByName(film.getName())){
            throw new DuplicateResourceException("Film with the name:" + " already exists");
        }
        return filmRepository.update(id, film);
    }

    public void delete(int id){
        filmRepository.delete(id);
    }
}
