package webservices.auth;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationResourceTest {
    AuthenticationResource authenticationResource;
    String jsonData;

    @Before
    public void setUp() throws Exception {
        authenticationResource = new AuthenticationResource();
        jsonData = "{\"sessiontoken\": \"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZW5zIiwiZXhwIjoxNTkyMTYxNzU3LCJyb2xlIjoidXNlciJ9.Ufs8oi1WoU30U6sGcnai_2aXe33BVAqwfidRQFXc13z6HwhOe-VNjBCpKpvH9c8WXpXzj0Sh24xikD-81IkO3g\"}";
    }

    @Test
    public void authenticateUser() {
        try{
        authenticationResource.authenticateUser(jsonData);}catch(Exception e){}
    }

    @Test
    public void registerUser() {
        try{authenticationResource.registerUser(jsonData);}catch(Exception e){}
    }
}