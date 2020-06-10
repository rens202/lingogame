package persistence;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ScoreDaoImplTest extends PostgresBaseDao {
    ScoreDao scoreDao;
    Connection connection;

    @Before
    public void setUp() {
        scoreDao = new ScoreDaoImpl();
        connection = super.getConnection();
    }

    @Test
    public void connectionNotNull(){
        assertNotNull(connection);
    }

    @Test
    public void postScore() {
        JSONObject json = new JSONObject();
        json.put("turns", 6);
        json.put("name", "Rens");
        json.put("wordlist", 1);
        String jsonString = json.toString();
        assertTrue(scoreDao.postScore(jsonString));
    }

    @Test
    public void getScores() {
        assertNotNull(scoreDao.getScores(1));
    }
}