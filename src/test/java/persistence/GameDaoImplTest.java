package persistence;

import domain.Game;
import domain.User;
import domain.Word;
import domain.Wordlist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameDaoImplTest {
    GameDao gameDao;
    Game game;
    User user;
    Word word;
    @Mock
    ResultSet res;

    @Before
    public void setUp() throws Exception {
        gameDao = new GameDaoImpl();
        word = new Word(1, "asfd", new Wordlist(1));
        user = new User(1, "username");
        game = new Game(word, 1, 2, user);

    }

    @Test
    public void startGame() {
        try{gameDao.startGame(game);}catch(Exception e){}
    }

    @Test
    public void getGameByUser() {
        try{gameDao.getGameByUser(user);}catch(Exception e){}
    }

    @Test
    public void updateGame() {
        try{gameDao.updateGame(game);}catch(Exception e){}
    }

    @Test
    public void resultset() throws SQLException {
        Mockito.when(res.getInt("id")).thenReturn(1);
        assertNotNull(gameDao.resultSetToGame(res, user));
    }
}