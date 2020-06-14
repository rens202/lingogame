package webservices;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

import static org.junit.Assert.*;

public class WordsResourceTest {
    WordsResource wordsResource;
    SecurityContext securityContext;

    @Before
    public void setUp() throws Exception {
        wordsResource = new WordsResource();
        securityContext = new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        };
    }

    @Test
    public void getWordLists() {
        try{
        wordsResource.getWordLists(securityContext);
    }catch(Exception e){}}
}