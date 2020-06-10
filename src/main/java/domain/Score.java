package domain;

public class Score {
	private int id;
	private String username;
	private Wordlist wordlist;
	private int turns;

	public Score(int id2, String name, Wordlist wordlist, int turns) {
		this.id = id2;
		this.username = name;
		this.wordlist = wordlist;
		this.turns = turns;
	}
	
	public String toString() {
		String s = "";
		s += username;
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
		return username;
	}
	
	public int getTurns() {
		return turns;
	}

	
	

}
