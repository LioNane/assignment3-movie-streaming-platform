package model;

import exception.InvalidInputException;

public class Film extends Content implements Playable{
    private int duration;

    public Film(int id, String name, int duration, float rating){
        super(id, name, rating);
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public float getRating(){ return super.getRating();}
    public void setRating(float rating){super.setRating(rating);}

    @Override
    public int countDuration(){
         return duration;
    }
    @Override
    public String getContentType() {
        return "Film";
    }
    @Override
    public void displayInfo(){
        System.out.println(getContentType() + ":" + "\n" +
                "ID: " + getId() + "\n" +
                "Title: " + getName() + "\n" +
                "Rating: " + getRating() + "/10" + "\n" +
                "Duration: " + countDuration());
    }

    @Override
    public void validate() throws InvalidInputException {
        super.validate();
        if (duration <= 0){
            throw new InvalidInputException("Invalid input");
        }
    }

    @Override
    public void play(){
        System.out.println("Play film: " + getName());
    }

    public boolean isHighlyRated(){
        return super.isHighlyRated();
    }

}
