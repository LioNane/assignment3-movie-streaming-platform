package model;

import exception.InvalidInputException;

public class Film extends Content implements Playable{
    private int duration;

    public Film(int id, String name, String author, int duration, double rating){
        super(id, name, author, rating);
        this.duration = duration;
    }

    @Override
    public int countDuration(){
         return duration;
    }
    @Override
    public String getContentType() {
        return "Film";
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

}
