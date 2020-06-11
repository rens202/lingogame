package persistence;

import domain.Word;
import domain.Wordlist;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordsDaoImplTest extends PostgresBaseDao{
    WordsDao wordsDao;
    Connection connection;

    @Before
    public void setUp() {
        wordsDao = new WordsDaoImpl();
        connection = super.getConnection();
    }

    @Test
    public void connectionNull(){
        assertNull(connection);
    }

    @Test
    public void getWordLists() {
        //ArrayList<Wordlist> wordlists = wordsDao.getWordLists();
        //assertNotNull(wordlists);
    }
}