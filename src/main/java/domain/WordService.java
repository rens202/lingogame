package domain;

public class WordService {

 	public Word createWord(int wordid, String word, Wordlist wordlist) {
		Word result = new Word(wordid, word, wordlist);
		return result;
	}

    public Wordlist createWordlist(String name, int id, Language language) {
        Wordlist result = new Wordlist(id, name, language);
        return result;
    }


	public Wordlist createWordlist(int wordlistId) {
		Wordlist result = new Wordlist(wordlistId);
		return result;
	}

}
