package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WordServiceTest extends WordService{
    WordService wordService = new WordService();
    Language language = new Language();

    @Test
    public void createWord() {
        Wordlist wordlist = wordService.createWordlist("name", 1, language);
        assertNotNull(wordlist);
        Word word = wordService.createWord(1, "word", wordlist);
        assertNotNull(word);
        assertEquals(wordlist, word.getWordlist());


    }
    @Test
    public void createWordList() {
        assertNotNull(wordService.createWordlist(1));
    }
}