package model;


import exception.InvalidInputException;

public class Episode implements Validatable, Playable{
    private int id;
    private String name;
    private int duration;
    private int series_id;

    public Episode(int id, String name, int duration, int series_id){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.series_id = series_id;
    }

    public Episode(int id, String name, int duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getSeries_id() {
        return series_id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void validate() throws InvalidInputException {
        if(id <= 0 || name.isEmpty() || duration <= 0){
            throw new InvalidInputException("Invalid input");
        }
    }
    @Override
    public void play(){
        System.out.println("Play episode: " + name);
    }
}
