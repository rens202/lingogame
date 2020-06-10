package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordlistTest {
    Language language = new Language();
    Wordlist wordlist = new Wordlist(1, "name", language);


    @Test
    public void getId() {
        assertEquals(1, wordlist.getId());
    }

    @Test
    public void setId() {
        assertEquals(1, wordlist.getId());
        wordlist.setId(2);
        assertEquals(2, wordlist.getId());
    }

    @Test
    public void getName() {
        assertEquals("name", wordlist.getName());
    }
}