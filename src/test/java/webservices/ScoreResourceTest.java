package webservices;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

import static org.junit.Assert.*;

public class ScoreResourceTest {
    ScoreResource scoreResource;

    @Before
    public void setUp() throws Exception {
        scoreResource = new ScoreResource();
    }

    @Test
    public void getScores() {
        scoreResource.getScores(1);
    }

    @Test
    public void getAllScores() {
        scoreResource.getScores(1);
    }
}