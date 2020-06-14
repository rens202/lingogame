package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageServiceTest {
    LanguageService languageService;

    @Before
    public void before(){
        languageService= new LanguageService();
    }

    @Test
    public void createLanguage() {
        Language lan = languageService.createLanguage("name", "NM", 1);
        assertNotNull(lan);

    }
}