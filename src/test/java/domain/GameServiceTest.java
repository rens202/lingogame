package domain;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameServiceTest {
    GameService gameService;
    Game game;
    User user;
    Word word;

    @Before
    public void before(){
        gameService = new GameService();
        word = new Word(1, "asfd", new Wordlist(1));
        user = new User(1, "username");
        game = gameService.createGame(word, user);
    }

    @Test
    public void startGame() {
        try {
            assertNotNull(gameService.startGame(1, "username"));
        }catch(Exception e){}

    }

    @Test
    public void checkWinner(){
        gameService.checkWinner(game, "asfd");
    }

    @Test
    public void postScore(){
        try{
        gameService.postScore(game);}catch(Exception e){}

    }

    @Test
    public void getGuessedWord(){
        assertNotNull( gameService.getGuessedWord(new JSONArray()));
    }

    @Test
    public void checkInput(){
        assertNotNull(gameService.checkInput(1, "a", game, "abrakadabra"));
        assertNotNull(gameService.checkInput(2, "a", game, "abrakadabra"));
        assertNotNull(gameService.checkInput(1, "u", game, "abrakadabra"));
    }

    @Test
    public void postTurn() {
        try{
            gameService.postTurn("{\"index\":\"0\"}", "username");
        }catch(Exception e){}
    }

    @Test
    public void occurences() {
       assertEquals(4, gameService.occurences("aaaa", 'a'));
    }

    @Test
    public void createJsonString() {
        assertNotNull(gameService.createJsonString(game));
    }

    @Test
    public void getTurn() {
        try{
        gameService.getTurn("username");
        }catch(Exception e){}
    }
}