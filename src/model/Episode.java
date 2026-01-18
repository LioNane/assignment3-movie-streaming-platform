package model;



public class Episode {
    private int id;
    private String name;
    private int duration;

    public Episode(int id, String name, int duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

}
