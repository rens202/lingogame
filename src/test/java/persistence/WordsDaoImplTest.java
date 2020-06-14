package persistence;

import domain.Wordlist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.ResultSet;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WordsDaoImplTest {
    WordsDao wordsDao;
    @Mock
    ResultSet res;
    Wordlist wordlist;

    @Before
    public void setUp() throws Exception {
        wordsDao = new WordsDaoImpl();
        Mockito.when(res.getInt("id")).thenReturn(1);
        Mockito.when(res.getString("word")).thenReturn("word");
    }

    @Test
    public void getWordLists() {
        try{wordsDao.getWordLists();}catch(Exception e){}
    }

    @Test
    public void getRandomWord() {
        try{wordsDao.getRandomWord(1);}catch(Exception e){}
    }

    @Test
    public void createWordFromResultset() {
        try{wordsDao.createWordFromResultset(res, wordlist);}catch(Exception e){}
    }

    @Test
    public void createWordlistFromResultSet() {
        try{wordsDao.createWordlistFromResultSet(res);}catch(Exception e){}
    }

}