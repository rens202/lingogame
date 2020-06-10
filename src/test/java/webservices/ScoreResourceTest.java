package webservices;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ScoreResourceTest {
    ScoreResource scoreResource;
    String jsonString;

    @Before
    public void setUp() {
        scoreResource = new ScoreResource();
        JSONObject json = new JSONObject();
        json.put("turns", 6);
        json.put("name", "Rens");
        json.put("wordlist", 1);
        jsonString = json.toString();
    }

    @Test
    public void postAndGetScore() {
        assertNotNull(scoreResource.postScore(jsonString));
        assertNotNull(scoreResource.getScores(1));
    }


}