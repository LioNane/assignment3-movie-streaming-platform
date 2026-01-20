package service;

import exception.DuplicateResourceException;
import model.Film;
import model.Series;
import repository.FilmRepository;
import repository.SeriesRepository;

import java.util.ArrayList;

public class SeriesService {
    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository){
        this.seriesRepository = seriesRepository;
    }

    public Series create(Series series){
        series.validate();
        if(seriesRepository.existsByName(series.getName())){
            throw new DuplicateResourceException("Series with the name:" + " already exists");
        }
        return seriesRepository.create(series);
    }

    public ArrayList<Series> getAll(){
        return seriesRepository.getAll();
    }

    public Series getById(int id){
        return seriesRepository.getById(id);
    }

    public Series update(int id, Series series){
        series.validate();
        if(seriesRepository.existsByName(series.getName())){
            throw new DuplicateResourceException("Film with the name:" + " already exists");
        }
        return seriesRepository.update(id, series);
    }

    public void delete(int id){
        seriesRepository.delete(id);
    }
}
