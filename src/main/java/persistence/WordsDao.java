package persistence;

import domain.Word;
import domain.Wordlist;

import java.sql.ResultSet;
import java.util.ArrayList;


public interface WordsDao {

    ArrayList<Wordlist> getWordLists();

	Word getRandomWord(int wordlistId);

	 Word createWordFromResultset(ResultSet res, Wordlist wordlist);

	Wordlist createWordlistFromResultSet(ResultSet res);
}
