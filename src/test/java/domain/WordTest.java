package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    Wordlist wordlist = new Wordlist(1);
    Word word = new Word(1, "test", wordlist);

    @Test
    public void getWord() {
        assertEquals("test", word.getWord());

    }

    @Test
    public void getWordlist() {
        assertEquals(wordlist, word.getWordlist());
    }
}