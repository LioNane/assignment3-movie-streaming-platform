package service;

import exception.DuplicateResourceException;
import model.Episode;
import repository.*;

import java.util.ArrayList;

public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository){
        this.episodeRepository = episodeRepository;
    }

    public Episode createEpisode(Episode episode){
        episode.validate();


        if(episodeRepository.existsBySeriesIdAndEpisodeName(episode.getSeriesId(), episode.getName())){
            throw new DuplicateResourceException("Episode with the name: " + episode.getName() + " already exists in series_id = " + episode.getSeriesId());
        }
        return episodeRepository.create(episode);
    }

    public ArrayList<Episode> getAllEpisodes(){
        return episodeRepository.getAll();
    }

    public Episode getEpisodeById(int id){
        return episodeRepository.getById(id);
    }

    public Episode updateEpisode(int id, Episode episode){
        episode.validate();

        return episodeRepository.update(id, episode);
    }

    public void deleteEpisode(int id){
        episodeRepository.delete(id);
    }
}
