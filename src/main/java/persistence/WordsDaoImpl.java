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
					"select wl.id, wl.name, languages.id as languageid, languages.name as languagename, languages.code as code from wordlists wl inner join languages on languages.id = wl.language where exists (select word from words where wordlist = wl.id)");
			ResultSet res = pst.executeQuery();
			if (res != null) {
				while (res.next()) {
					String name = res.getString("name");
					int languageId = res.getInt("languageId");
					int id = res.getInt("id");
					String code = res.getString("code");
					String languageName = res.getString("languageName");
					Wordlist wl = wordService.createWordlist(name, id,
							languageService.createLanguage(languageName, code, languageId));
					result.add(wl);
				}
			}
			con.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Word> getWordsFromList(int id) {
		ArrayList<Word> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"select words.id as id, words.word as word, words.wordlist as wordlist, wordlists.name as wordlistname, wordlists.language as language, languages.code as languagecode, languages.name as languagename from words inner join wordlists on wordlists.id = words.wordlist inner join languages on languages.id = wordlists.language where words.wordlist = ?");
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();

			if (res != null) {
				while (res.next()) {
					result.add(CreateWordFromResultset(res))
;				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
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
					result = CreateWordFromResultset(res);
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return result;

	}

	public Word CreateWordFromResultset(ResultSet res) {
		Word result = null;
		try {
			int wordlistid = res.getInt("wordlist");
			int wordid = res.getInt("id");
			String word = res.getString("word");
			String wordlistName = res.getString("wordlistname");
			int languageId = res.getInt("language");
			String languageCode = res.getString("languageCode");
			String languageName = res.getString("languageName");
			result = (wordService.createWord(wordid, word, wordService.createWordlist(wordlistName, wordlistid,
					languageService.createLanguage(languageName, languageCode, languageId))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
