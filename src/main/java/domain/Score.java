package domain;

public class Score {
	private int id;
	private User user;
	private Wordlist wordlist;
	private int turns;

	public Score( User user, Wordlist wordlist, int turns) {
		this.user = user;
		this.wordlist = wordlist;
		this.turns = turns;
	}
	
	public Score(int scoreId, User user2, Wordlist createWordlist, int turns2) {
		this.id = scoreId;
		this.user = user2;
		this.wordlist = createWordlist;
		this.turns = turns2;
	}

	public String toString() {
		String s = "";
		s += user.getUsername();
		s += " ";
		s += turns;
		return s;
	}

	public Wordlist getWordlist() {
		return wordlist;
	}

	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public int getTurns() {
		return turns;
	}

	public User getUser() {
		return this.user;
	}

	
	

}
