package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageTest {
    Language language;
    Language language2;

    @Before
    public void before(){
        language = new Language();
        language2 = new Language(1, "Nederlands", "NED");
    }

    @Test
    public void getCode() {
        assertNull(language.getCode());
        assertNotNull(language2.getCode());

    }

    @Test
    public void getId() {
        assertEquals(0, language.getId());
        assertNotNull(language2.getId());
    }

    @Test
    public void getName() {
        assertNull(language.getName());
        assertNotNull(language2.getName());
    }
}