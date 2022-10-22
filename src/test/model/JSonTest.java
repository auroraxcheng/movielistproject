package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSonTest {
    protected void checkMovie(String name, MovieGenre genre, int runtime, Movie movie) {
        assertEquals(name, movie.getName());
        assertEquals(genre, movie.getGenre());
        assertEquals(runtime, movie.getRuntime());
    }
}
