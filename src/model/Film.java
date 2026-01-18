package model;

public class Film extends Content{
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

}
