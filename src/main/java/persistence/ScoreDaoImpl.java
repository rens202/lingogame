package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import domain.Score;
import domain.ScoreService;
import domain.User;
import domain.Wordlist;

public class ScoreDaoImpl extends PostgresBaseDao implements ScoreDao {
	ScoreService scoreService = new ScoreService();

	@Override
	public Boolean postScore(Score score) {
		Boolean result = false;
		try (Connection con = super.getConnection()) {

			PreparedStatement pst = con.prepareStatement("INSERT INTO scores(\"user\", wordlist, turns) values(?, ?, ?)");
			pst.setInt(1, score.getUser().getId());
			pst.setInt(2, score.getWordlist().getId());
			pst.setInt(3, score.getTurns());
			int res = pst.executeUpdate();
			if (res == 1) {
				result = true;
			}
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<Score> getScores(int wordlist) {
		ArrayList<Score> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"SELECT wordlist, scores.id as scoreId, \"user\", turns, users.username as username FROM scores inner join users on users.id = scores.\"user\" where wordlist = ?");
			pst.setInt(1, wordlist);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				result.add(resultSetToScore(res));
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<Score> getScores() {
		ArrayList<Score> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"SELECT wordlist, scores.id as scoreId, \"user\", turns, users.username as username FROM scores inner join users on users.id = scores.\"user\"");
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				result.add(resultSetToScore(res));
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return result;
	}

	private Score resultSetToScore(ResultSet res) {
		Score result = null;
		try {
			int wordlistId = res.getInt("wordlist");
			int scoreId = res.getInt("scoreId");
			String username = res.getString("username");
			int userId = res.getInt("user");
			int turns = res.getInt("turns");
			result = scoreService.createScore(scoreId, new User(userId, username), wordlistId, turns);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
