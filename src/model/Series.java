package model;

import exception.InvalidInputException;

import java.util.ArrayList;

public class Series extends Content implements Playable{
    private ArrayList<Episode> episodes;

    public Series(int id, String name, float rating){
        super(id, name, rating);
        episodes = new ArrayList<>();
    }

    public Series(int id, String name, float rating, ArrayList<Episode> episodes){
        super(id, name, rating);
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

    @Override
    public void validate() throws InvalidInputException {
        super.validate();
        if(episodes.getFirst() == null){
            throw new InvalidInputException("Invalid input");
        }
    }

    @Override
    public void play(){
        System.out.println("Play first episode: " + episodes.getFirst().getName());
    }

}
