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

    public int getId(){
        return super.getId();
    }
    public void setId(int id){
        super.setId(id);
    }

    public String getName(){
        return super.getName();
    }


    public float getRating(){ return super.getRating();}
    public void setRating(float rating){super.setRating(rating);}

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
    public void displayInfo(){
        System.out.println(getContentType() + ":" + "\n" +
                "ID: " + getId() + "\n" +
                "Title: " + getName() + "\n" +
                "Rating: " + getRating() + "/10" + "\n" +
                "Duration: " + countDuration() + "\n");
    }

    @Override
    public void validate() throws InvalidInputException {
        super.validate();
    }

    @Override
    public void play(){
        if (episodes.isEmpty()){
            System.out.println("No episodes");
        } else {
            System.out.println("Play first episode: " + episodes.getFirst().getName());
        }
    }

    public boolean isHighlyRated(){
        return super.isHighlyRated();
    }

}
