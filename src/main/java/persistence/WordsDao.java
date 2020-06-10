package persistence;

import domain.Word;
import domain.Wordlist;
import java.util.ArrayList;


public interface WordsDao {

    ArrayList<Wordlist> getWordLists();

	ArrayList<Word> getWordsFromList(int id);
}
