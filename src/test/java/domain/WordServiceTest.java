package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordServiceTest {
    WordService wordService;
    Word word;
    Wordlist wordlist;
    Wordlist wordlist2;

    @Before
    public void before(){
        wordService = new WordService();

    }

    @Test
    public void createWordlist() {
        wordlist = wordService.createWordlist(1);
    }

    @Test
    public void createWordlist1() {
        wordlist2 = wordService.createWordlist("name", 1, new Language());
    }

    @Test
    public void createWord() {
        word = wordService.createWord(1, "name", wordlist);
    }

}