package persistence;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import domain.Score;
import domain.User;
import domain.Wordlist;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ScoreDaoImplTest extends PostgresBaseDao {
    ScoreDao scoreDao;
    Connection connection;
    Score score;
    
    @Before
    public void setUp() {
        scoreDao = new ScoreDaoImpl();
        connection = super.getConnection();
        score = new Score(new User(2, "rens"), new Wordlist(1), 0);
    }

    @Test
    public void connectionNull(){
        assertNull(connection);
    }

    @Test
    public void postScore() {
       // assertTrue(scoreDao.postScore(score));
    }

    @Test
    public void getScores() {
        //assertNotNull(scoreDao.getScores(1));
    }
}