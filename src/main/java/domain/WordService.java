package domain;

public class WordService {

    public Word createWord(String word, Wordlist wordlist){
        Word result = new Word(word, wordlist);
        return result;
    }
    
	public Word createWord(int wordid, String word, Wordlist wordlist) {
		Word result = new Word(wordid, word, wordlist);
		return result;
	}

    public Wordlist createList(String fileName, Language language){
        Wordlist result;
        result = new Wordlist(fileName, language);
        return result;
    }

    public Wordlist createWordlist(String name, int id, Language language) {
        Wordlist result = new Wordlist(id, name, language);
        return result;
    }


	public Wordlist createWordlist(String wordListName, Language createLanguage) {
		Wordlist result = new Wordlist(wordListName, createLanguage);
        return result;
		
	}

	public Wordlist createWordList(int wordlistId) {
		Wordlist result = new Wordlist(wordlistId);
		return result;
	}

}
