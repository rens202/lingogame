package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageServiceTest {
    LanguageService languageService = new LanguageService();

    @Test
    public void createLanguage(){
        assertNotNull(languageService.createLanguage("Nederlands","NED",1));
    }



}