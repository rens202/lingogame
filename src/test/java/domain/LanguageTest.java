package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageTest {

    Language language = new Language(1, "Nederlands", "NED");
    Language languageWithoutAttribute = new Language();
    Language languageId = new Language(2);

    @Test
    public void getCode() {
        assertEquals("", language.getCode(), "NED");
        assertNull(languageWithoutAttribute.getCode());
        assertNull(languageId.getCode());
    }

    @Test
    public void getId() {
        assertEquals(language.getId(), 1);
        assertEquals(languageId.getId(), 2);
    }

    @Test
    public void getName() {
        assertEquals(language.getName(), "Nederlands");
        assertNull(languageWithoutAttribute.getName());
        assertNull(languageId.getName());
    }
}