package webservices;


import org.junit.Before;
import org.junit.Test;

import webservices.auth.MySecurityContext;

import static org.junit.Assert.*;

import javax.ws.rs.core.SecurityContext;

public class WordsResourceTest {
    WordsResource wordsResource;
    MySecurityContext msc;

    @Before
    public void setUp() {
        wordsResource = new WordsResource();
        msc = new MySecurityContext("rens", "rle", true);
    }

    @Test
    public void getWordLists() {
        assertEquals(200, wordsResource.getWordLists(msc).getStatus());
    }

   
}