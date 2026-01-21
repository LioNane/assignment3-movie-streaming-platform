package model;


import exception.InvalidInputException;

abstract class Content implements Validatable{
    private int id;
    private String name;
    private float rating;

    protected Content(int id, String name, float rating){
        setId(id);
        setName(name);
        setRating(rating);
    }

    protected int getId(){
        return id;
    }
    protected void setId(int id){
        this.id = id;
    }

    protected String getName(){
        return name;
    }
    protected void setName(String name){
        this.name = name;
    }

    protected float getRating(){ return rating;}
    protected void setRating(float rating){this.rating = rating;}

    abstract protected int countDuration();
    abstract protected String getContentType();
    abstract protected void displayInfo();

    protected boolean isHighlyRated(){
        return (rating >= 8.0);
    }

    @Override
    public void validate() throws InvalidInputException {
        if(id <= 0 || name.isEmpty() || rating < 0){
            throw new InvalidInputException("Invalid input");
        }
    }
}
