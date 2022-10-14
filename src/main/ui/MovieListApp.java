package ui;

import model.Movie;
import model.MovieGenre;
import model.MovieList;
import org.w3c.dom.ls.LSOutput;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MovieListApp {

    private MovieList movielist;
    private Movie m1;
    private Movie m2;
    private Scanner input;

    // runs movie list tracking application
    public MovieListApp() {
        runMovieListApp();
    }

    //modifies: this
    // effects: takes instructions from users and uses it to run the application
    private void runMovieListApp() {
        boolean keepGoing = true;
        String instructions = null;

        starttracker();

        while (keepGoing) {
            displayMenu();
            instructions = input.next();
            instructions = instructions.toLowerCase();

            if (instructions.equals("q")) {
                keepGoing = false;
            } else {
                processInstructions(instructions);
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void processInstructions(String instructions) {
        if (instructions.equals("d")) {
            deleteFromList();
        } else if (instructions.equals("a")) {
            addToList();
        } else if (instructions.equals("m")) {
            moveLists();
        } else if (instructions.equals("n")) {
            numMovies();
        } else if (instructions.equals("rt")) {
            runtimeOfList();
        } else {
            System.out.println("Unable to perform instruction");
        }
    }

    // modifies: this
    // effects: initializes the movie list
    private void starttracker() {
        movielist = new MovieList("Aurora's list");
        m1 = new Movie("Spirited Away", MovieGenre.ANIME, 200);
        m2 = new Movie("Perfect Blue", MovieGenre.HORROR, 100);

        movielist.addWatchedMovie(m1); // added Spirited Away to already watched
        movielist.addToWatchMovie(m2); // added Perfect Blue to towatch list

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // effects: displays menu of options
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> delete from list ");
        System.out.println("\ta -> add to the to-watch list");
        System.out.println("\tm -> move from towatch to watched list");
        System.out.println("\tn -> number of movies in list");
        System.out.println("\trt -> runtime of list");
        System.out.println("\tq -> quit");
    }

    // modifies: this
    // effects: deletes a movie from selected list. If movie is not in the list,
    // nothing will be printed out
    private void deleteFromList() {
        List<Movie> selected = selectList();
        System.out.println("Enter movie name");
        System.out.println("If movie has been removed successfully, there will be a confirmation message.");
        String moviename = input.next();

        for (Movie m : selected) {
            if (moviename.equals(m.getName())) {
                selected.remove(m);
                System.out.println("Movie removed");
            }
        }
    }

    // modifies: this
    // effects: adds a movie to the unwatched list
    private void addToList() {
        System.out.println("Enter movie name");
        System.out.println("Enter movie genre (FANTASY, SCIFI, HORROR, ACTION, COMEDY, ANIME");
        System.out.println("Enter movie duration");

        String moviename = input.next();
        String genre = input.next();
        int runtime = input.nextInt();

        Movie newmovie = new Movie(moviename, MovieGenre.valueOf(genre), runtime);
        movielist.addToWatchMovie(newmovie);
        System.out.println("Movie added successfully. Here is the list:");
        printMovieNames(movielist.getToWatchList());
    }

    // modifies: towatchlist and already watched list
    // effects: moves movie from towatch to already watched list. If movie is not
    // in the list, nothing will print out.
    private void moveLists() {
        System.out.println("Enter movie name: ");
        System.out.println("If movie has been moved successfully, there will be a confirmation message.");
        String moviename = input.next();


        for (Movie m : movielist.getToWatchList()) {
            if (moviename.equals(m.getName())) {
                movielist.moveMovie(m);
                System.out.println("Movie has been moved. Watched list: ");
                printMovieNames(movielist.getAlreadyWatchedList());
            }
        }
    }

    // effects: shows user how many movies are in each list
    private void numMovies() {
        List<Movie> selected = selectList();
        printNumMoviesInList(selected);
    }

    // effects: shows user total runtime of movies in each list
    private void runtimeOfList() {
        List<Movie> selected = selectList();
        printMovieRuntime(selected);
    }

    // effects: selects between watched and towatch list
    private List<Movie> selectList() {
        String selection = "";

        while (!(selection.equals("tw") || selection.equals("w"))) {
            System.out.println("tw for to-watch list");
            System.out.println("w for already watched list");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("tw")) {
            return movielist.getToWatchList();
        } else {
            return movielist.getAlreadyWatchedList();
        }
    }

    // EFFECTS: prints the total number of movies in a list
    private void printNumMoviesInList(List<Movie> selected) {
        System.out.println(selected.size());
    }

    // effects: prints the total runtime of all movies in a list
    private void printMovieRuntime(List<Movie> selected) {
        int time = 0;
        for (Movie m : selected) {
            time = time + m.getRuntime();
        }
        System.out.println(time);
    }

    // effects: prints out names of all movies in the list
    private void printMovieNames(List<Movie> selected) {
        for (Movie m : selected) {
            System.out.println(m.getName());
        }
    }
}
