package persistence;

import domain.Game;
import domain.User;

public interface GameDao {

	Boolean startGame(Game game);

	Game getGameByUser(User user);

	Boolean updateGame(Game game);

}
