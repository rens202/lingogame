package domain;

public class ScoreService {
	WordService wordService = new WordService();
	
	public Score createScore(int id, String name, int wordlistId, int turns) {
		Score result;
		result = new Score(id, name, wordService.createWordList(wordlistId), turns);
		return result;
	}

}