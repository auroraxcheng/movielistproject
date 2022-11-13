package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JSonWriterTest extends JSonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            MovieList ml = new MovieList("My movie list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MovieList ml = new MovieList("My movie list");
            JsonWriter writer = new JsonWriter("./data/testWriterMovieListEmpty.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMovieListEmpty.json");
            ml = reader.read();
            assertEquals("My movie list", ml.getTitle());
            assertEquals(0, ml.numUnwatchedMovies());
            assertEquals(0, ml.numWatchedMovies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MovieList ml = new MovieList("My movie list");
            ml.addToWatchMovie(new Movie("Spirited Away", MovieGenre.ANIME, 100));
            ml.addWatchedMovie(new Movie("American Psycho", MovieGenre.HORROR, 150));
            ml.addWatchedMovie(new Movie("Star Wars", MovieGenre.FANTASY, 300));
            JsonWriter writer = new JsonWriter("./data/testWriterMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMovieList.json");
            ml = reader.read();
            assertEquals("My movie list", ml.getTitle());
            List<Movie> towatchlist = ml.getToWatchList();
            assertEquals(1, towatchlist.size());
            checkMovie("Spirited Away", MovieGenre.ANIME, 100, towatchlist.get(0));

            List<Movie> watchedlist = ml.getAlreadyWatchedList();
            assertEquals(2, watchedlist.size());
            checkMovie("American Psycho", MovieGenre.HORROR, 150, watchedlist.get(0));
            checkMovie("Star Wars", MovieGenre.FANTASY, 300, watchedlist.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
