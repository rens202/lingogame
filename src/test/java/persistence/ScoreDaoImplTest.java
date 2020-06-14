package persistence;

import domain.Language;
import domain.Score;
import domain.User;
import domain.Wordlist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class ScoreDaoImplTest {
    ScoreDao scoreDao;
    Score score;
    Wordlist wordlist;
    User user;
    @Mock ResultSet res;

    @Before
    public void setUp() throws Exception {
        scoreDao = new ScoreDaoImpl();
        wordlist = new Wordlist(1, "name", new Language());
        user = new User(1, "Rens");
        score = new Score(user ,wordlist, 5);
    }

    @Test
    public void postScore() {
        try{
        scoreDao.postScore(score);}catch(Exception e){}
    }

    @Test
    public void getScores() {
        try{scoreDao.getScores(1);}
        catch(Exception e){}
    }

    @Test
    public void getScores1() {
        try{scoreDao.getScores();}catch(Exception e){}
    }
    
    @Test
    public void toScore() throws SQLException{
		Mockito.when(res.getString("username")).thenReturn("username");
    	scoreDao.resultSetToScore(res);
    }
}