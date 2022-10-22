package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JSonReaderTest extends JSonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderMovieListEmpty.json");
        try {
            MovieList ml = reader.read();
            assertEquals("My movie list", ml.getTitle());
            assertEquals(0, ml.numWatchedMovies());
            assertEquals(0, ml.numUnwatchedMovies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testMovieList.json");
        try {
            MovieList ml = reader.read();
            assertEquals("My movie list", ml.getTitle());
            List<Movie> towatchlist = ml.getToWatchList();
            assertEquals(1, towatchlist.size());
            checkMovie("Spirited Away", MovieGenre.ANIME, 100, towatchlist.get(0));

            List<Movie> watchedlist = ml.getAlreadyWatchedList();
            assertEquals(2, watchedlist.size());
            checkMovie("American Psycho", MovieGenre.HORROR, 150, watchedlist.get(0));
            checkMovie("Star Wars", MovieGenre.FANTASY, 300, watchedlist.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
