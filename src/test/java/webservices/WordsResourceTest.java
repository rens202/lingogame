package webservices;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordsResourceTest {
    WordsResource wordsResource;

    @Before
    public void setUp() {
        wordsResource = new WordsResource();
    }

    @Test
    public void getWordLists() {
        assertEquals(200, wordsResource.getWordLists().getStatus());
    }

    @Test
    public void getWordsFromList() {
        assertEquals(200, wordsResource.getWordsFromList(1).getStatus());
    }
}