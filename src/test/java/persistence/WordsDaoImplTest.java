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
    public void connectionNotNull(){
        assertNotNull(connection);
    }

    @Test
    public void getWordLists() {
        ArrayList<Wordlist> wordlists = wordsDao.getWordLists();
        assertNotNull(wordlists);
        assertNotNull(wordlists.get(0).getId());
    }

    @Test
    public void getWordsFromList() {
        ArrayList<Word> words = wordsDao.getWordsFromList(1);
        assertNotNull(words);
        assertNotNull(words.get(0).getWord());
    }
}