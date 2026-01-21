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

    public Series createSeries(Series series){
        series.validate();
        if(seriesRepository.existsByName(series.getName())){
            throw new DuplicateResourceException("Series with the name: " + series.getName() + " already exists");
        }
        return seriesRepository.create(series);
    }

    public ArrayList<Series> getAllSeries(){
        return seriesRepository.getAll();
    }

    public Series getSeriesById(int id){
        return seriesRepository.getById(id);
    }

    public Series updateSeries(int id, Series series){
        series.validate();
        return seriesRepository.update(id, series);
    }

    public void deleteSeries(int id){
        seriesRepository.delete(id);
    }
}
