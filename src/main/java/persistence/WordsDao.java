package persistence;

import domain.Word;
import domain.Wordlist;
import java.util.ArrayList;


public interface WordsDao {
    Boolean sendWords(ArrayList<Word> words);

    ArrayList<Wordlist> getWordLists();

	ArrayList<Word> getWordsFromList(int id);

	Boolean deleteWordList(int id);

	Boolean addWord(int id, String newword);
	
	Boolean postWords(String json);
}
