package domain;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.GameDao;
import persistence.GameDaoImpl;
import persistence.ScoreDao;
import persistence.ScoreDaoImpl;
import persistence.UserDao;
import persistence.UserDaoImpl;
import persistence.WordsDao;
import persistence.WordsDaoImpl;

public class GameService {
	WordsDao wordsDao = new WordsDaoImpl();
	GameDao gameDao = new GameDaoImpl();
	UserDao userDao = new UserDaoImpl();

	public Boolean startGame(int id, String username) {
		Boolean result = false;
		Word word = wordsDao.getRandomWord(id);
		User user = userDao.findByUsername(username);
		Game game = createGame(word, user);
		if (gameDao.startGame(game)) {
			result = true;
		}

		return result;
	}

	public String postTurn(String jsonString, String username) {
		String result = "";
		User user = userDao.findByUsername(username);
		Game game = gameDao.getGameByUser(user);
		JSONObject object = new JSONObject(jsonString);
		JSONArray c = object.getJSONArray("input");
		JSONArray arrayResult = new JSONArray();
		String guessedWord = getGuessedWord(c);
		
		System.out.println("Received User, Game & Guessedword!");

		Boolean isWinner = checkWinner(game, guessedWord);
		
		if (!isWinner) {
			System.out.println("Is not a winner, processing input....");
			for (int i = 0; i < c.length(); i++) {
				JSONObject obj = c.getJSONObject(i);
				int inputIndex = (int) obj.get("index");
				String inputInput = obj.get("input").toString();
				arrayResult.put(checkInput(inputIndex, inputInput, game, guessedWord));
			}
		}

		game.addTurn();

		if (isWinner) {
			game.setStatus(2);
			postScore(game);
		} else if (game.getStatus() == 0) {
			game.setStatus(1);
		}

		if (gameDao.updateGame(game)) {
			JSONObject res = createJsonString(game);
			res.put("results", arrayResult);
			result = res.toString();
		}

		return result;

	}
	
	private void postScore(Game game) {
		System.out.println("winner");
		ScoreService scoreService = new ScoreService();
		Score score = scoreService.createScore(game);
		ScoreDao scoreDao = new ScoreDaoImpl();
		scoreDao.postScore(score);
	}

	private String getGuessedWord(JSONArray c) {
		String result = "";
		for (int i = 0; i < c.length(); i++) {
			JSONObject obj = c.getJSONObject(i);
			result += obj.get("input").toString();
			;
		}

		return result;
	}

	private Boolean checkWinner(Game game, String guessedWord) {
		Boolean result = false;
		if (game.getWord().getWord().toLowerCase().equals(guessedWord.toLowerCase())) {
			result = true;
		}
		return result;
	}

	private JSONObject checkInput(int location, String input, Game game, String guessedWord) {
		JSONObject json = new JSONObject();
		String word = game.getWord().getWord();
		if (word.charAt(location) == input.charAt(0)) {
			json.put("index", location);
			json.put("status", 2);
		} else if (word.contains(input)
				&& occurences(word, input.charAt(0)) >= occurences(guessedWord, input.charAt(0))) { // TODO CONTAINS >	INPUT SO YOU CANT spAM EEEEEEEEE
			json.put("index", location);
			json.put("status", 1);
		} else {
			json.put("index", location);
			json.put("status", 0);
		}

		return json;

	}

	public int occurences(String word, char charInput) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == charInput) {
				count++;
			}
		}
		return count;
	}

	public Game createGame(Word word, User user) {
		int status = 0;
		int turn = 0;
		Game result = new Game(word, status, turn, user);

		return result;
	}

	public JSONObject createJsonString(Game game) {
		JSONObject json = new JSONObject();
		json.put("wordlength", game.getWord().getWord().length());
		json.put("turn", game.getTurn());
		json.put("id", game.getId());
		json.put("status", game.getStatus());
		json.put("listname", game.getWord().getWordlist().getName());
		return json;
	}

	public String getTurn(String username) {
		String result = "";
		User user = userDao.findByUsername(username);
		Game game = gameDao.getGameByUser(user);

		if (game.getTurn() == 0 && game.getStatus() != 2) {
			result = createJsonString(game).toString();
		} else {
			result = createJsonString(game).toString();
		}

		return result;

	}

}
