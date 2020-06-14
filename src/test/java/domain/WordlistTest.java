package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordlistTest {
    Wordlist wordlist;
    Language language;
    Wordlist wordlist2;

    @Before
    public void before(){
        wordlist = new Wordlist(1);
        language = new Language();
        wordlist2 = new Wordlist(1, "name", language);
    }

    @Test
    public void getId() {
        assertNotNull(wordlist.getId());
    }

    @Test
    public void setId() {
        wordlist.setId(2);
        assertNotEquals(wordlist, wordlist2);
    }

    @Test
    public void getName() {
        assertNull(wordlist.getName());
        assertNotNull(wordlist2.getName());
    }

    @Test
    public void getLanguage() {
        assertNull(wordlist.getLanguage());
        assertNotNull(wordlist2.getLanguage());

    }
}