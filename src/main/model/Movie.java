package model;

public class Movie {

    private String name;
    private MovieGenre genre;
    private int runtime;

    // MODIFIES: this
    // EFFECTS: creates a new movie with name, genre and runtime
    public Movie(String name, MovieGenre genre, int runtime) {
        this.name = name;
        this.genre = genre;
        this.runtime = runtime;
    }

    //getters
    public String getName() {
        return name;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public int getRuntime() {
        return runtime;
    }
}
