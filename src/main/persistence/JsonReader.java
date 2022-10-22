package persistence;

import jdk.jfr.Category;
import model.Movie;
import model.MovieGenre;
import model.MovieList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// CITATION: JsonReader example

public class JsonReader {
    private String source;

    // effects: makes a reader to read source file
    public JsonReader(String source) {
        this.source = source;
    }

    // effects: reads and returns the list stored in file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return returnMovieList(jsonObject);
    }

    // effects: reads file data and returns it as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // effects: parses stored list from JSON object and returns it
    private MovieList returnMovieList(JSONObject jsonObject) {
        String name = jsonObject.getString("listtitle");
        MovieList ml = new MovieList(name);
        addToWatchMovies(ml, jsonObject);
        addWatchedMovies(ml, jsonObject);
        return ml;
    }

    // modifies: movielist
    // effects: parses movies from JSOn object and adds them to towatch list
    private void addToWatchMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("towatchmovies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addToWatchMovie(ml, nextMovie);
        }
    }

    // modifies: movielist
    // effects: parses movie from JSOn object and adds them to towatch list
    private void addToWatchMovie(MovieList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieGenre genre = MovieGenre.valueOf(jsonObject.getString("genre"));
        int runtime = jsonObject.getInt("runtime");

        Movie movie = new Movie(name, genre, runtime);
        ml.addToWatchMovie(movie);
    }

    // modifies: movielist
    // effects: parses movies from JSOn object and adds them watched to list
    private void addWatchedMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("watchedmovies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addWatchedMovie(ml, nextMovie);
        }
    }

    // modifies: movielist
    // effects: parses movie from JSOn object and adds them to watched list
    private void addWatchedMovie(MovieList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieGenre genre = MovieGenre.valueOf(jsonObject.getString("genre"));
        int runtime = jsonObject.getInt("runtime");
        Movie movie = new Movie(name, genre, runtime);

        ml.addWatchedMovie(movie);
    }
}

