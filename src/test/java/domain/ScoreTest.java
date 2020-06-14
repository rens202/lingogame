package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    Score score;
    Score score2;
    User user;
    Wordlist wordlist;
    int turns = 3;
    int id = 1;

    @Before
    public void toDo(){
        wordlist = new Wordlist(1, "name", new Language());
        user = new User(1, "Rens");
        score = new Score(user ,wordlist, turns);
        score2 = new Score(id, user, wordlist, turns);
    }

    @Test
    public void getWordlist() {
        assertNotNull(score.getWordlist());
        assertNotNull(score2.getWordlist());
        assertEquals(score.getWordlist(), score2.getWordlist());
    }

    @Test
    public void getId() {
        assertEquals(0, score.getId());
        assertNotNull(score2.getId());
    }

    @Test
    public void getUsername() {
        assertNotNull(score.getUsername());
        assertNotNull(score2.getUsername());
        assertEquals(score.getUsername(), score2.getUsername());

    }

    @Test
    public void getTurns() {
        assertNotNull(score.getTurns());
        assertNotNull(score2.getTurns());
        assertEquals(score.getTurns(), score2.getTurns());
    }

    @Test
    public void getUser() {
        assertNotNull(score.getUser());
        assertNotNull(score2.getUser());
        assertEquals(score.getUser(), score2.getUser());
    }

    @Test
    public void toStringTest(){
        assertNotNull(score.toString());
    }
}