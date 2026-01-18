package model;


abstract class Content {
    private int id;
    private String name;
    private String author;
    private double rating;

    protected Content(int id, String name, String author, double rating){
        setId(id);
        setName(name);
        setAuthor(author);
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

    protected String getAuthor(){
        return author;
    }
    protected void setAuthor(String Author){
        this.author = author;
    }

    protected double getRating(){ return rating;}
    protected void setRating(double rating){this.rating = rating;}

    abstract protected int countDuration();
    abstract protected String getContentType();

    protected void displayBaseInfo(){
        System.out.println("Type:" + getContentType() + "\n" +
                            "ID: " + id + "\n" +
                            "Title: " + name + "\n" +
                            "Author: " + author + "\n" +
                            "Rating: " + rating + "/10" + "\n" +
                            "Duration: " + countDuration());
    }
}
