package persistence;

import java.util.ArrayList;

import domain.Score;

public interface ScoreDao {

	Boolean postScore(String jsonData);

	ArrayList<Score> getScores(int wordlist);

}
