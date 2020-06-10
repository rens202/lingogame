package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    Score score = new Score(1, "Rens", new Wordlist(1), 5);

    @Test
    public void toStringTest(){
        assertNotNull(score.toString());
    }

    @Test
    public void getWordlist() {
        assertNotNull(score.getWordlist());
    }

    @Test
    public void getId() {
        assertEquals(1, score.getId());
    }

    @Test
    public void getUsername() {
        assertEquals("", "Rens", score.getUsername());
    }

    @Test
    public void getTurns() {
        assertEquals(5, score.getTurns());
    }
}