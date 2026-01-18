package model;

import java.util.ArrayList;

public class Series extends Content{
    private ArrayList<Episode> episodes;

    public Series(int id, String name, String author, double rating, Episode episode){
        super(id, name, author, rating);
        episodes = new ArrayList<>();
        episodes.add(episode);
    }

    public Series(int id, String name, String author, double rating, ArrayList<Episode> episodes){
        super(id, name, author, rating);
        this.episodes = episodes;
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    @Override
    public int countDuration(){
        int duration = 0;
        for (Episode episode: episodes){
            duration += episode.getDuration();
        }
        return duration;
    }
    @Override
    public String getContentType(){
        return "Series";
    }


}
