package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;
    User user2;
    User user3;

    @Before
    public void before() {
        user = new User("username","password");
        user2 = new User(1,"username");
        user3 = new User(1, "username","password");
    }

    @Test
    public void setPassword() {
        assertNull(user2.getPassword());
        user2.setPassword("password");
        assertNotNull(user2.getPassword());
    }

    @Test
    public void getId() {
        assertEquals(0, user.getId());
        user.setId(1);
        assertNotNull(user.getId());

    }
    @Test
    public void toStringTest(){
        assertNotNull(user.getUsername());
        assertNotNull(user.toString());
    }

}