package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    Word word;
    Wordlist wordlist;

    @Before
    public void before(){
        wordlist = new Wordlist(1, "name", new Language());
        word = new Word(1, "word", wordlist);
    }

    @Test
    public void getWord() {
        assertNotNull(word.getWord());
        assertEquals("word", word.getWord());
    }

    @Test
    public void getWordlist() {
        assertNotNull(word.getWordlist());
        assertEquals(wordlist, word.getWordlist());
    }

    @Test
    public void getId() {
        assertNotNull(word.getId());
        assertEquals(1, word.getId());
    }
}