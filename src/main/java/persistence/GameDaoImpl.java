package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Game;
import domain.User;
import domain.Word;
import domain.WordService;
import domain.Wordlist;

public class GameDaoImpl extends PostgresBaseDao implements GameDao {
	
	@Override
	public Boolean startGame(Game game) {
		Boolean result = false;
				
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("INSERT INTO games(word, turn, status, \"user\") values(?,?,?,?)");
			pst.setInt(1, game.getWord().getId());
			pst.setInt(2, game.getTurn());
			pst.setInt(3, game.getStatus());
			pst.setInt(4, game.getUser().getId());
			int res = pst.executeUpdate();
			if (res == 1) {
				result = true;
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public Game getGameByUser(User user) {
		Game result = null;
		WordService wordService = new WordService();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("SELECT games.*, words.word as wordname, words.wordlist From games inner join words on words.id = games.word where games.\"user\" = ? and status != 2 order by id desc LIMIT 1");
			pst.setInt(1, user.getId());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int turn = rs.getInt("turn");
				int word = rs.getInt("word");
				String wordname = rs.getString("wordname");
				int wordlist = rs.getInt("wordlist");
				int status = rs.getInt("status");
				result = new Game(wordService.createWord(word, wordname, wordService.createWordlist(wordlist)), status, turn, user, id);
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		return result;
	}

	@Override
	public Boolean updateGame(Game game) {
		Boolean result = false;
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("UPDATE games set turn = ?, status = ? where id = ?");
			pst.setInt(1, game.getTurn());
			pst.setInt(2, game.getStatus());
			pst.setInt(3, game.getId());
			int res = pst.executeUpdate();
			if (res == 1) {
				result = true;
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}
	
	
	
	

}
