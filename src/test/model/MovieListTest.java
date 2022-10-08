package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {
    private MovieList testlist;
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private Movie m4;
    private Movie m5;

    @BeforeEach
    public void setup() {
    testlist = new MovieList("Aurora's list");

    m1 = new Movie("Spirited Away", MovieGenre.ANIME, 200);
    m2 = new Movie("Perfect Blue", MovieGenre.HORROR, 100);
    m3 = new Movie("Star Wars", MovieGenre.ACTION, 250);
    m4 = new Movie("Naruto", MovieGenre.ANIME, 300);
    m5 = new Movie("Interstellar", MovieGenre.SCIFI, 100);

    testlist.addWatchedMovie(m1);
    testlist.addToWatchMovie(m2);

    }

    @Test
    public void addWatchedMovieTest() {
        assertTrue(testlist.getAlreadyWatchedList().contains(m1));
    }

    @Test
    public void addMultipleWatchedMovieTest() {
        assertTrue(testlist.getAlreadyWatchedList().contains(m1));
        testlist.addToWatchMovie(m3);
        assertFalse(testlist.getAlreadyWatchedList().contains(m3));
        testlist.addWatchedMovie(m2);
        assertTrue(testlist.getAlreadyWatchedList().contains(m2));
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
    public void removeMultipleWatchedMovieTest() {
        testlist.addWatchedMovie(m3);
        testlist.addWatchedMovie(m5);
        testlist.removeWatchedMovie(m1);
        assertFalse(testlist.getAlreadyWatchedList().contains(m1));
        testlist.removeWatchedMovie(m5);
        assertFalse(testlist.getAlreadyWatchedList().contains(m5));
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
    public void totalNoUnwatchedMoviesTest() {
        testlist.removeToWatchMovie(m2);
        assertEquals(0, testlist.totalUnwatchedMovies());
    }

    @Test
    public void totalAlreadyWatchedMoviesTest() {
        testlist.addWatchedMovie(m3);
        assertEquals(2, testlist.totalAlreadyWatchedMovies());
    }

    @Test
    public void totalNoAlreadyWatchedMoviesTest() {
        testlist.removeWatchedMovie(m1);
        assertEquals(0, testlist.totalAlreadyWatchedMovies());
    }

    @Test
    public void totalUnwatchedMovieTimes() {
        assertEquals(100, testlist.totalUnwatchedMovieTime());
    }

    @Test
    public void totalWatchedMovieTimes() {
        testlist.addWatchedMovie(m4);
        assertEquals(500, testlist.totalAlreadyWatchedMovieTime());
    }

    @Test
    public void totalNoWatchedMovieTime() {
        testlist.removeWatchedMovie(m1);
        assertEquals(0, testlist.totalAlreadyWatchedMovieTime());
    }


    @Test
    public void totalMultipleUnwatchedMovieTimes() {
        testlist.addToWatchMovie(m3);
        testlist.addToWatchMovie(m5);
        assertEquals(450, testlist.totalUnwatchedMovieTime());
    }

}

