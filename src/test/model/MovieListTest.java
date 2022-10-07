package model;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {
    private MovieList testlist;
    private Movie m1;
    private Movie m2;

    @BeforeEach
    public void setup() {
    testlist = new MovieList("Aurora's list");

    m1 = new Movie("Spirited Away", MovieGenre.ANIME, 200);
    m2 = new Movie("Perfect Blue", MovieGenre.HORROR, 100);

    testlist.addWatchedMovie(m1);
    testlist.addToWatchMovie(m2);

    }

    @Test
    public void addWatchedMovieTest() {
        assertTrue(testlist.getAlreadyWatchedList().contains(m1));
    }

    @Test
    public void addToWatchMovieTest() {
        assertTrue(testlist.getToWatchList().contains(m2));
    }

    @Test
    public void removeWatchedMovieTest() {
        testlist.removeWatchedMovie(m1);
        assertFalse(testlist.getAlreadyWatchedList().contains(m1));
    }


    @Test
    public void removeToWatchMovieTest() {
        testlist.removeToWatchMovie(m2);
        assertFalse(testlist.getToWatchList().contains(m2));
    }

    @Test
    public void totalUnwatchedMoviesTest() {
        assertEquals(1, testlist.totalUnwatchedMovies());
    }

    @Test
    public void totalUnwatchedMovieTimes() {
        assertEquals(100, testlist.totalUnwatchedMovieTime());
    }

}

