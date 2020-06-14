package persistence;

import domain.Game;
import domain.User;

import java.sql.ResultSet;

public interface GameDao {

	Boolean startGame(Game game);

	Game getGameByUser(User user);

	Boolean updateGame(Game game);

	Game resultSetToGame(ResultSet rs, User user);

}
