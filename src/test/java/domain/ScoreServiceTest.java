package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreServiceTest {
    ScoreService scoreService = new ScoreService();

    @Test
    public void createScore() {
        assertNotNull(scoreService.createScore(new User(2, "Rens"), 5, 5));


    }
}