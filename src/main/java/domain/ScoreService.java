package domain;

public class ScoreService {
	WordService wordService = new WordService();

	public Score createScore(int scoreId, User user, int wordlist, int turns) {
		Score result = new Score(scoreId, user, wordService.createWordlist(wordlist), turns);
		return result;
	}
	
	public Score createScore(Game game) {
		Score result = new Score(game.getUser(), game.getWord().getWordlist(), game.getTurn());
		return result;
	}

}
