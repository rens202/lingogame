package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import domain.Score;
import domain.ScoreService;

public class ScoreDaoImpl extends PostgresBaseDao implements ScoreDao {
	ScoreService scoreService = new ScoreService();

	@Override
	public Boolean postScore(String jsonData) {
		Boolean result = false;
		try (Connection con = super.getConnection()) {

			JSONObject object = new JSONObject(jsonData);

			String name = object.get("name").toString();
			int turns = (int) object.get("turns");
			int wordlist = (int) object.get("wordlist");

			PreparedStatement pst = con.prepareStatement("INSERT INTO scores(name, wordlist, turns) values(?, ?, ?)");
			pst.setString(1, name);
			pst.setInt(2, wordlist);
			pst.setInt(3, turns);
			int res = pst.executeUpdate();
			if (res == 1) {
				result = true;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<Score> getScores(int wordlist) {
		ArrayList<Score> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("SELECT wordlist, id, name, turns FROM scores where wordlist = ?");
			pst.setInt(1, wordlist);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				int wordlistId = res.getInt("wordlist");
				int scoreId = res.getInt("id");
				String name = res.getString("name");
				int turns = res.getInt("turns");
				result.add(scoreService.createScore(scoreId, name, wordlistId, turns));
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

}
