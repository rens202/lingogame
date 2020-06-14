package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    Game game2;
    User user;
    Word word;

    @Before
    public void before(){
        word = new Word(1, "asfd", new Wordlist(1));
        user = new User(1, "username");
        game = new Game(word, 1, 2, user);
        game2 = new Game(word, 1, 2, user, 1);
    }

    @Test
    public void setId() {
        assertEquals(0, game.getId());
        game.setId(1);
        assertEquals(1, game.getId());
    }

    @Test
    public void getWord() {
        assertNotNull(game.getWord());
    }

    @Test
    public void getTurn() {
        assertEquals(game.getTurn(),game2.getTurn());
        game.addTurn();
        assertNotEquals(game.getTurn(),game2.getTurn());
    }

    @Test
    public void getStatus() {
        assertEquals(game.getStatus(),game2.getStatus());
        game.setStatus(0);
        assertNotEquals(game.getStatus(),game2.getStatus());

    }

    @Test
    public void getUser() {
        assertEquals(game.getUser(),game2.getUser());
    }




}