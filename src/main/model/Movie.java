package model;

import org.json.JSONObject;
import persistence.Writable;

public class Movie implements Writable {

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("genre", genre);
        json.put("runtime", runtime);
        return json;
    }
}
