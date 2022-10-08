package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    private Movie testMovie;

    @BeforeEach
    public void setup() {
        testMovie = new Movie("Frozen", MovieGenre.FANTASY, 160);
    }
    @Test
    public void testGetters() {
        assertEquals(testMovie.getName(), "Frozen");
        assertEquals(testMovie.getGenre(), MovieGenre.FANTASY);
        assertEquals(testMovie.getRuntime(), 160);
    }
}