package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreServiceTest {
    ScoreService scoreService;
    Game game;
    User user;
    Score score;
    Score score2;
    Word word;

    @Before
    public void before(){
        scoreService = new ScoreService();
        user = new User("username", "password");
        word = new Word(1, "asdf", new Wordlist(3));
        game = new Game(word, 1, 1, user);

    }

    @Test
    public void createScore() {
        score = scoreService.createScore(game);

    }

    @Test
    public void createScore1() {
        score2 = scoreService.createScore(1, user, 1, 1);
        assertNotEquals(score, score2);
    }
}