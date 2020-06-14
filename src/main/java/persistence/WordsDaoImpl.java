package persistence;

import domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordsDaoImpl extends PostgresBaseDao implements WordsDao {
	WordService wordService = new WordService();
	LanguageService languageService = new LanguageService();

	public ArrayList<Wordlist> getWordLists() {
		ArrayList<Wordlist> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"select wordlists.id as wordlist, wordlists.name as wordlistname, wordlists.language as language, languages.code as languagecode, languages.name as languagename from wordlists inner join languages on languages.id = wordlists.language where exists (select word from words where wordlist = wordlists.id)");
			ResultSet res = pst.executeQuery();
			if (res != null) {
				while (res.next()) {
						Wordlist wl = createWordlistFromResultSet(res);
					result.add(wl);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Word getRandomWord(int wordlistId) {
		Word result = null;

		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"select words.id as id, words.word as word, words.wordlist as wordlist, wordlists.name as wordlistname, wordlists.language as language, languages.code as languagecode, languages.name as languagename from words inner join wordlists on wordlists.id = words.wordlist inner join languages on languages.id = wordlists.language where words.wordlist = ? ORDER BY random() LIMIT 1");
			pst.setInt(1, wordlistId);
			ResultSet res = pst.executeQuery();

			if (res != null) {
				while (res.next()) {
					Wordlist resultwl = createWordlistFromResultSet(res);
					result = createWordFromResultset(res, resultwl);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Word createWordFromResultset(ResultSet res, Wordlist wordlist) {
		Word result = null;
		try {
			int wordid = res.getInt("id");
			String word = res.getString("word");
			result = (wordService.createWord(wordid, word, wordlist));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Wordlist createWordlistFromResultSet(ResultSet res){
		Wordlist result = null;
		try {
		int wordlistid = res.getInt("wordlist");
		String wordlistName = res.getString("wordlistname");
		int languageId = res.getInt("language");
		String languageCode = res.getString("languageCode");
		String languageName = res.getString("languageName");
		result = wordService.createWordlist(wordlistName, wordlistid,
				languageService.createLanguage(languageName, languageCode, languageId));
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return result;}

}
