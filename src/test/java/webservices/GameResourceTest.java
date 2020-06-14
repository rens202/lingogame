package webservices;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.SecurityContext;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

import static org.junit.Assert.*;

public class GameResourceTest {
    GameResource gameResource;
    SecurityContext securityContext;

    @Before
    public void setUp() throws Exception {
        gameResource = new GameResource();
        securityContext = new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public boolean equals(Object another) {
                        return false;
                    }

                    @Override
                    public String toString() {
                        return null;
                    }

                    @Override
                    public int hashCode() {
                        return 0;
                    }

                    @Override
                    public String getName() {
                        return "aaaa";
                    }
                };
            }

            @Override
            public boolean isSecure() {
                return true;
            }


			@Override
			public String getAuthenticationScheme() {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public boolean isUserInRole(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

  
        };
    }

    @Test
    public void startGame() {
        try{
        gameResource.startGame(securityContext, 1);}catch(Exception e){}
    }

    @Test
    public void postTurn() {
        try{gameResource.postTurn("", securityContext);}catch(Exception e){}
    }

    @Test
    public void getTurn() {
        try{gameResource.getTurn(securityContext);}catch(Exception e){}
    }
}