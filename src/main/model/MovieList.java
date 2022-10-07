package model;


import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieList {
    private String listtitle;
    private List<Movie> towatchlist;
    private List<Movie> alreadywatchedlist;

    public MovieList(String listtitle) {
        this.listtitle = listtitle;
        towatchlist = new LinkedList<>();
        alreadywatchedlist = new LinkedList<>();
    }

    // getters
    public String getTitle() {
        return this.listtitle;
    }

    public List<Movie> getToWatchList() {
        return towatchlist;
    }

    public List<Movie> getAlreadyWatchedList() {
        return alreadywatchedlist;
    }

    // MODIFIES: this
    // effects: adds a movie to watch list
    public void addToWatchMovie(Movie m) {
        this.towatchlist.add(m);
    }

    // MODIFIES: this
    // effects: removes a movie to watch list
    public void removeToWatchMovie(Movie m) {
        this.towatchlist.remove(m);
    }

    // MODIFIES: this
    // effects: adds a movie to already watched list
    public void addWatchedMovie(Movie m) {
        this.alreadywatchedlist.add(m);
    }

    // MODIFIES: this
    // effects: removes a movie already watch list
    public void removeWatchedMovie(Movie m) {
        this.alreadywatchedlist.remove(m);
    }

    //effects: prints out list of genres of movies in watched list
    public void watchedGenres() {
        System.out.println("List of genres:");
        for (Movie g : alreadywatchedlist) {
            System.out.println(g.getGenre());
        }
    }


    public int totalWatchedMovies () {
        int sum = 0;
        for (Movie m : alreadywatchedlist) {
            sum++;
        }
        return sum;
    }

    public int totalUnwatchedMovies () {
        int sum = 0;
        for (Movie m : towatchlist) {
            sum++;
        }
        return sum;
    }

    public int totalUnwatchedMovieTime() {
        int time = 0;
        for (Movie m : towatchlist) {
            time = time + m.getRuntime();
        }
        return time;
    }
}




