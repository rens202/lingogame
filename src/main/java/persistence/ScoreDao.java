package persistence;

import java.util.ArrayList;

import domain.Score;

public interface ScoreDao {


	ArrayList<Score> getScores(int wordlist);

	ArrayList<Score> getScores();

	Boolean postScore(Score score);

}
