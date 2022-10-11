package model;


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
    // effects: adds a movie to to-watch list
    public void addToWatchMovie(Movie m) {
        this.towatchlist.add(m);
    }

    // MODIFIES: this
    // effects: removes a movie from to-watch list
    public void removeToWatchMovie(Movie m) {
        this.towatchlist.remove(m);
    }

    // MODIFIES: this
    // effects: adds a movie to already watched list
    public void addWatchedMovie(Movie m) {
        this.alreadywatchedlist.add(m);
    }

    // MODIFIES: this
    // effects: removes a movie from already watched watch list
    public void removeWatchedMovie(Movie m) {
        this.alreadywatchedlist.remove(m);
    }

    // modifies: towatchlist and alreadywatchedlist
    // effects: moves movie from unwatched to watched list
    public void moveMovie(Movie m) {
        if (towatchlist.contains(m)) {
            towatchlist.remove(m);
            alreadywatchedlist.add(m);
        } else {
            System.out.println("Movie isn't in to-watch list");
        }
    }
}





