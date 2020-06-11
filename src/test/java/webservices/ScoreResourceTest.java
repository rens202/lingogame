package webservices;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import domain.Score;
import domain.User;
import domain.Wordlist;
import webservices.auth.MySecurityContext;

import static org.junit.Assert.*;

public class ScoreResourceTest {
    ScoreResource scoreResource;
    Score score;
    MySecurityContext msc;
    

    @Before
    public void setUp() {
        scoreResource = new ScoreResource();
        score = new Score(new User(2, "rens"), new Wordlist(1), 0);
        msc = new MySecurityContext("rens", "rle", true);
        
    }
    
    public void getScore() {
    	assertNotNull(scoreResource.getAllScores());
    	assertNotNull(scoreResource.getScores(1));
    }

 
}