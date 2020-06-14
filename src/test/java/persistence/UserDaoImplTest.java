package persistence;

import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {
    UserDao userDao;
    User user;
    @Mock
    ResultSet res;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDaoImpl();
        user = new User(1, "asdf");
    }

    @Test
    public void findByUsername() {
        try{
        userDao.findByUsername("asdf");}catch(Exception e){}
    }

    @Test
    public void saveNewUser() {
        try{userDao.saveNewUser(user);}catch(Exception e){}
    }

    @Test
    public void checkUser() {
        try{userDao.checkUser("username","password");}catch(Exception e){}
    }

    @Test
    public void toUser()throws SQLException {
        Mockito.when(res.getInt("id")).thenReturn(1);
        Mockito.when(res.getString("password")).thenReturn("password");
        userDao.toUser(res);
    }
}