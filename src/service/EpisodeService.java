package service;

import exception.DuplicateResourceException;
import model.Episode;
import repository.*;

import java.util.ArrayList;

public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final SeriesRepository seriesRepository;

    public EpisodeService(SeriesRepository seriesRepository, EpisodeRepository episodeRepository){
        this.episodeRepository = episodeRepository;
        this.seriesRepository = seriesRepository;
    }

    public Episode create(Episode episode){
        episode.validate();

        seriesRepository.getById(episode.getSeriesId());

        if(episodeRepository.existsBySeriesIdAndEpisodeName(episode.getSeriesId(), episode.getName())){
            throw new DuplicateResourceException("Episode with the name:" + " already exists in series_id = " + episode.getSeriesId());
        }
        return episodeRepository.create(episode);
    }

    public ArrayList<Episode> getAll(){
        return episodeRepository.getAll();
    }

    public Episode getById(int id){
        return episodeRepository.getById(id);
    }

    public Episode update(int id, Episode episode){
        episode.validate();
        if(seriesRepository.existsByName(episode.getName())){
            throw new DuplicateResourceException("Film with the name:" + " already exists");
        }
        return episodeRepository.update(id, episode);
    }

    public void delete(int id){
        episodeRepository.delete(id);
    }
}
